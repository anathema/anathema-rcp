package net.sf.anathema.basics.eclipse.ui;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;

public final class TopPartListener implements IPartListener {

  private final Runnable runnable;
  private IEditorPart topPart;

  public TopPartListener(Runnable runnable) {
    this.runnable = runnable;
  }

  @Override
  public void partActivated(IWorkbenchPart part) {
    // nothing to do
  }

  @Override
  public void partBroughtToTop(IWorkbenchPart part) {
    if (part instanceof IEditorPart) {
      topPart = (IEditorPart) part;
      runnable.run();
    }
  }

  @Override
  public void partClosed(IWorkbenchPart part) {
    if (part == topPart) {
      topPart = null;
      runnable.run();
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
}