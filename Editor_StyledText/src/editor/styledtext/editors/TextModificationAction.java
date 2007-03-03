package editor.styledtext.editors;

import org.eclipse.jface.action.Action;

public class TextModificationAction extends Action implements IStyledTextAction {

  private IStyledTextEditor editor;
  private final ITextModification modification;

  public TextModificationAction(String text, ITextModification modification) {
    super(text);
    this.modification = modification;
  }

  public void setEditor(IStyledTextEditor editor) {
    this.editor = editor;
  }

  @Override
  public void run() {
    editor.modifySelection(modification);
  }
}