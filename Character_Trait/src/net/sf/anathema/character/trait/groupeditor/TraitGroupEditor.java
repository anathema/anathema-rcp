package net.sf.anathema.character.trait.groupeditor;

import java.util.List;

import net.sf.anathema.basics.eclipse.resource.ResourceChangeListenerDisposable;
import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.basics.item.editor.AbstractItemEditorControl;
import net.sf.anathema.basics.item.editor.AbstractPersistableItemEditorPart;
import net.sf.anathema.basics.item.editor.IEditorControl;
import net.sf.anathema.basics.swt.layout.GridDataFactory;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.listening.CharacterPartNameListener;
import net.sf.anathema.character.core.traitview.IExtendableIntValueView;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.ColumnLayout;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class TraitGroupEditor extends AbstractPersistableItemEditorPart<IItem> {

  private final ClassedProvider<ITraitGroupEditorDecoration> decorations = new ClassedProvider<ITraitGroupEditorDecoration>();

  @Override
  protected IEditorControl createItemEditorControl() {
    return new AbstractItemEditorControl(this) {

      @Override
      public void setFocus() {
        // nothing to do
      }

      @Override
      public void createPartControl(Composite parent) {
        FormToolkit toolkit = new FormToolkit(parent.getDisplay());
        Form form = toolkit.createForm(parent);
        toolkit.decorateFormHeading(form);
        form.setText(getEditorInput().getName());
        Color background = toolkit.getColors().getBackground();
        form.getBody().setLayout(new FillLayout());
        final Composite container = new Composite(form.getBody(), SWT.NONE);
        container.setBackground(background);
        decorations.addAll(new TraitGroupEditorDecorationFactory().create());
        ITraitGroupEditorInput editorInput = (ITraitGroupEditorInput) getEditorInput();
        ICharacterId characterId = editorInput.getCharacterId();
        List<IDisplayTraitGroup<IInteractiveTrait>> displayGroups = editorInput.createDisplayGroups();
        ColumnLayout columnLayout = new ColumnLayout();
        columnLayout.maxNumColumns = displayGroups.size();
        container.setLayout(columnLayout);
        for (IDisplayTraitGroup<IInteractiveTrait> group : displayGroups) {
          final Composite groupContainer = new Composite(container, SWT.NONE);
          groupContainer.setLayout(new GridLayout(3, false));
          TraitViewFactory factory = new TraitViewFactory(groupContainer, editorInput.getImageProvider(), characterId);
          groupContainer.setBackground(background);
          createLabel(groupContainer, background, GridDataFactory.createHorizontalSpanData(3)).setText(
              editorInput.getConfiguration().getGroupLabel(group));
          for (final IInteractiveTrait trait : group.getTraits()) {
            String label = editorInput.getConfiguration().getTraitLabel(trait.getTraitType());
            final IExtendableIntValueView view = factory.create(label, toolkit, trait);
            for (ITraitGroupEditorDecoration decoration : decorations) {
              decoration.decorate(trait, view, editorInput);
            }
            addDisposable(trait);
          }
        }
        IFolder characterFolder = editorInput.getCharacterFolder();
        Display display = container.getDisplay();
        final IResourceChangeListener resourceListener = new CharacterPartNameListener(
            TraitGroupEditor.this,
            characterFolder,
            display);
        ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceListener);
        addDisposable(new ResourceChangeListenerDisposable(resourceListener));
        container.setSize(container.computeSize(SWT.DEFAULT, SWT.DEFAULT));
        getSite().getPage().addPartListener(new DecorationUpdateListener((TraitGroupEditor) getEditor()));
      }

      private Label createLabel(Composite parent, Color background, GridData data) {
        Label label = new Label(parent, SWT.NULL);
        label.setLayoutData(data);
        label.setBackground(background);
        return label;
      }
    };
  }

  public void updateDecorations() {
    for (ITraitGroupEditorDecoration decoration : decorations) {
      decoration.update();
    }
  }
}