package net.sf.anathema.character.description;

import static net.sf.anathema.character.description.Messages.*;
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
import org.eclipse.ui.forms.widgets.FormToolkit;

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
      protected void createPartControl(FormToolkit toolkit, Composite body) {
        body.setLayout(new GridLayout(2, false));
        ICharacterDescription item = getItem();
        nameView = initSingleLineText(body, toolkit, CharacterDescriptionEditor_Name, item.getName());
        initSingleLineText(body, toolkit, CharacterDescriptionEditor_Player, item.getPlayer());
        initSingleLineText(body, toolkit, CharacterDescriptionEditor_Concept, item.getConcept());
        initSingleLineText(body, toolkit, CharacterDescriptionEditor_Periphrasis, item.getPeriphrasis());
        initMultiLineText(body, toolkit, CharacterDescriptionEditor_Characterization, item.getCharacterization());
        initMultiLineText(body, toolkit, CharacterDescriptionEditor_PhysicalDescription, item.getPhysicalDescription());
        initMultiLineText(body, toolkit, CharacterDescriptionEditor_Notes, item.getNotes());
      }

      private void initMultiLineText(
          Composite parent,
          FormToolkit toolkit,
          String label,
          ITextualDescription description) {
        createLabel(parent, toolkit, label);
        ITextView view = SimpleTextView.createMultiLineView(parent, toolkit);
        addDisposable(new TextualPresenter(view, description)).initPresentation();
      }

      private ITextView initSingleLineText(
          Composite parent,
          FormToolkit toolkit,
          String label,
          ITextualDescription description) {
        createLabel(parent, toolkit, label);
        ITextView view = SimpleTextView.createSingleLineView(parent, toolkit);
        addDisposable(new TextualPresenter(view, description)).initPresentation();
        return view;
      }

      private void createLabel(Composite parent, FormToolkit toolkit, String text) {
        Label contentLabel = toolkit.createLabel(parent, text + ":"); //$NON-NLS-1$
        contentLabel.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
      }

      private ICharacterDescription getItem() {
        return getPersistableEditorInput().getItem();
      }
    };
  }
}