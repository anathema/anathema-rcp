package editor.styledtext.editors;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;

public class TextModificationAction extends Action implements IStyledTextAction {

  private IStyledTextEditor editor;
  private final ITextModification modification;

  public TextModificationAction(String text, ITextModification modification) {
    super(text, IAction.AS_CHECK_BOX);
    this.modification = modification;
    setChecked(false);
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
    setChecked(editor.isActiveFor(modification));
  }
}