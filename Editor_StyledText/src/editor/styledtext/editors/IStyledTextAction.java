package editor.styledtext.editors;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.TextSelection;

public interface IStyledTextAction extends IAction {

  public void setEditor(IStyledTextEditor editor);

  public void setState(TextSelection selection);
}