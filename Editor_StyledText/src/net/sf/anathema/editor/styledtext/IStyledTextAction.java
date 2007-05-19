package net.sf.anathema.editor.styledtext;

import org.eclipse.jface.action.IAction;

public interface IStyledTextAction extends IAction {

  public void setEditor(IStyledTextEditor editor);

  public void updateState();
}