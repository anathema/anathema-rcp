package net.sf.anathema.character.description;

import net.sf.anathema.basics.item.editor.AbstractItemEditorControl;
import net.sf.anathema.basics.item.editor.IEditorControl;
import net.sf.anathema.basics.item.editor.UpdatePartNameListener;
import net.sf.anathema.basics.jface.text.SimpleTextView;
import net.sf.anathema.character.core.editors.AbstractCharacterModelEditorPart;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.textualdescription.ITextView;
import net.sf.anathema.lib.textualdescription.ITextualDescription;
import net.sf.anathema.lib.textualdescription.TextualPresenter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;

public class CharacterDescriptionEditor extends AbstractCharacterModelEditorPart<ICharacterDescription> {

  public static final String EDITOR_ID = "net.sf.anathema.character.description.editor"; //$NON-NLS-1$

  @Override
  protected IEditorControl createItemEditorControl() {
    return new AbstractItemEditorControl(this) {
      private ITextView nameView;

      @Override
      public void setFocus() {
        nameView.setFocus();
      }

      @Override
      public void init(IEditorSite editorSite, IEditorInput input) {
        super.init(editorSite, input);
        final IObjectValueChangedListener<String> nameListener = new UpdatePartNameListener(
            CharacterDescriptionEditor.this);
        final ITextualDescription name = getItem().getName();
        addDisposable(new TextListenerDisposable(nameListener, name));
        name.addTextChangedListener(nameListener);
      }

      @Override
      public void createPartControl(Composite parent) {
        parent.setLayout(new GridLayout(2, false));
        nameView = initSingleLineText(parent, Messages.CharacterDescriptionEditor_Name, getItem().getName());
        initSingleLineText(parent, Messages.CharacterDescriptionEditor_Player, getItem().getPlayer());
        initSingleLineText(parent, Messages.CharacterDescriptionEditor_Concept, getItem().getConcept());
        initSingleLineText(parent, Messages.CharacterDescriptionEditor_Periphrasis, getItem().getPeriphrasis());
        initMultiLineText(parent, Messages.CharacterDescriptionEditor_Characterization, getItem().getCharacterization());
        initMultiLineText(
            parent,
            Messages.CharacterDescriptionEditor_PhysicalDescription,
            getItem().getPhysicalDescription());
        initMultiLineText(parent, Messages.CharacterDescriptionEditor_Notes, getItem().getNotes());
      }

      private void initMultiLineText(Composite parent, String label, ITextualDescription description) {
        createLabel(parent, label);
        ITextView view = SimpleTextView.createMultiLineView(parent);
        addDisposable(new TextualPresenter(view, description)).initPresentation();
      }

      private ITextView initSingleLineText(Composite parent, String label, ITextualDescription description) {
        createLabel(parent, label);
        ITextView view = SimpleTextView.createSingleLineView(parent);
        addDisposable(new TextualPresenter(view, description)).initPresentation();
        return view;
      }

      private void createLabel(Composite parent, String text) {
        Label contentLabel = new Label(parent, SWT.LEFT);
        contentLabel.setText(text + ":"); //$NON-NLS-1$
        contentLabel.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
      }

      private ICharacterDescription getItem() {
        return getPersistableEditorInput().getItem();
      }
    };
  }
}