/**
 * 
 */
package net.sf.anathema.basics.repository.linkage;

import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;

public final class TopPartListener implements IPartListener {
  
  private final IAction action;
  private IEditorPart topPart;

  public TopPartListener(IAction action) {
    this.action = action;
  }
  
  @Override
  public void partActivated(IWorkbenchPart part) {
    // nothing to do
  }

  @Override
  public void partBroughtToTop(IWorkbenchPart part) {
    if (part instanceof IEditorPart) {
      topPart = (IEditorPart) part;
      action.run();
    }
  }

  @Override
  public void partClosed(IWorkbenchPart part) {
    if (part == topPart) {
      topPart = null;
    }
  }

  @Override
  public void partDeactivated(IWorkbenchPart part) {
    // nothing to do
  }

  @Override
  public void partOpened(IWorkbenchPart part) {
    // nothing to do
  }
  
  public IEditorPart getTopPart() {
    return topPart;
  }
}