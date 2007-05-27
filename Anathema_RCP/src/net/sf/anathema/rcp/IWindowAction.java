package net.sf.anathema.rcp;

import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IWorkbenchWindow;

public interface IWindowAction extends IAction {

  public void setWorkbenchWindow(IWorkbenchWindow window);
}