package net.sf.anathema.character.backgrounds;

import net.sf.anathema.basics.item.editor.AbstractItemEditorControl;
import net.sf.anathema.basics.item.editor.IEditorControl;
import net.sf.anathema.character.backgrounds.model.IBackgroundAdditionListener;
import net.sf.anathema.character.backgrounds.model.IBackgroundModel;
import net.sf.anathema.character.core.editors.AbstractCharacterModelEditorPart;
import net.sf.anathema.character.core.traitview.IExtendableIntValueView;
import net.sf.anathema.character.trait.groupeditor.dynamic.DynamicTraitContainer;
import net.sf.anathema.character.trait.groupeditor.dynamic.IDynamicEditor;
import net.sf.anathema.character.trait.groupeditor.dynamic.TraitContainer;
import net.sf.anathema.character.trait.groupeditor.dynamic.TraitViewFactory;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;
import net.sf.anathema.lib.ui.IDisposable;

import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class BackgroundEditor extends AbstractCharacterModelEditorPart<IBackgroundModel> implements IDynamicEditor {

  private static final class TextChangingFocusListener implements FocusListener {

    private final Text textfield;

    public TextChangingFocusListener(Text textfield) {
      this.textfield = textfield;
    }

    @Override
    public void focusGained(FocusEvent e) {
      if (textfield.getText().equals(DEFAULT_TEXT)) {
        textfield.setText(EMPTY_TEXT);
      }
    }

    @Override
    public void focusLost(FocusEvent e) {
      if (textfield.getText().equals(EMPTY_TEXT)) {
        textfield.setText(DEFAULT_TEXT);
      }
    }
  }

  private static final String EMPTY_TEXT = ""; //$NON-NLS-1$
  private static final String DEFAULT_TEXT = Messages.BackgroundEditor_HINT_TEXT;
  private Composite layoutContainer;

  @Override
  protected IEditorControl createItemEditorControl() {
    return new AbstractItemEditorControl(this) {

      private Text entry;

      @Override
      public void createPartControl(final Composite parent) {
        final BackgroundEditorInput editorInput = (BackgroundEditorInput) getEditorInput();
        final FormToolkit toolkit = new FormToolkit(parent.getDisplay());
        Composite body = createParentContainer(parent, toolkit);
        createBackgroundEntry(editorInput, toolkit, body);
        createBackgroundTable(editorInput, toolkit, body);
      }

      private Composite createParentContainer(final Composite parent, final FormToolkit toolkit) {
        Form form = toolkit.createForm(parent);
        toolkit.decorateFormHeading(form);
        form.setText(getEditorInput().getName());
        final Composite container = form.getBody();
        container.setLayout(new GridLayout(1, false));
        return container;
      }

      private void createBackgroundEntry(
          final BackgroundEditorInput editorInput,
          final FormToolkit toolkit,
          final Composite container) {
        entry = toolkit.createText(container, DEFAULT_TEXT);
        entry.addSelectionListener(new SelectionListener() {
          @Override
          public void widgetDefaultSelected(SelectionEvent e) {
            editorInput.getItem().addBackground(entry.getText());
          }

          @Override
          public void widgetSelected(SelectionEvent e) {
            // nothing
          }
        });
        entry.addFocusListener(new TextChangingFocusListener(entry));
      }

      private void createBackgroundTable(
          final BackgroundEditorInput editorInput,
          final FormToolkit toolkit,
          final Composite body) {
        layoutContainer = toolkit.createComposite(body);
        layoutContainer.setLayout(new GridLayout(3, false));
        TraitViewFactory factory = new TraitViewFactory(
            layoutContainer,
            editorInput.getImageProvider(),
            editorInput.getCharacterId());
        TraitContainer traitContainer = new TraitContainer(toolkit, factory, BackgroundEditor.this);
        for (IInteractiveTrait trait : editorInput.getBackgrounds()) {
          traitContainer.addTrait(trait);
        }
        TraitContainer dynamicTraitContainer = new DynamicTraitContainer(toolkit, factory, BackgroundEditor.this);
        startListeningForNewBackgrounds(editorInput, dynamicTraitContainer);
      }

      private void startListeningForNewBackgrounds(final BackgroundEditorInput editorInput, final TraitContainer table) {
        final IBackgroundAdditionListener<IInteractiveTrait> additionListener = new IBackgroundAdditionListener<IInteractiveTrait>() {
          @Override
          public void traitAdded(IInteractiveTrait trait) {
            table.addTrait(trait);
            entry.setText(EMPTY_TEXT);
          }
        };
        addDisposable(new IDisposable() {
          @Override
          public void dispose() {
            editorInput.removeModificationListener(additionListener);
          }
        });
        editorInput.addModificationListener(additionListener);
      }

      @Override
      public void setFocus() {
        entry.setFocus();
        entry.setText(DEFAULT_TEXT);
        entry.selectAll();
      }
    };
  }

  @Override
  public void decorate(IInteractiveTrait trait, IExtendableIntValueView view) {
    // TODO Surplusmarking für Backgrounds
  }

  @Override
  public void redraw() {
    layoutContainer.pack();
    layoutContainer.layout();
  }
}