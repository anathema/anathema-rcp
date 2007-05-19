package net.sf.anathema.editor.styledtext;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;

public class TextModificationAction extends Action implements IStyledTextAction {

  private IStyledTextEditor editor;
  private final ITextModification modification;

  public TextModificationAction(String text, ITextModification modification) {
    super(text, IAction.AS_CHECK_BOX);
    this.modification = modification;
    updateState();
  }

  public void setEditor(IStyledTextEditor editor) {
    this.editor = editor;
  }

  @Override
  public void run() {
    editor.modifySelection(modification);
  }

  @Override
  public void updateState() {
    if (editor == null) {
      setEnabled(false);
      setChecked(false);
    }
    else {
      setEnabled(!editor.isSelectionEmpty());
      setChecked(editor.isActiveFor(modification));
    }
  }
}