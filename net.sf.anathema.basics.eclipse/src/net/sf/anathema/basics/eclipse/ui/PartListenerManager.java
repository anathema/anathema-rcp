package net.sf.anathema.basics.eclipse.ui;

import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWindowListener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;

public final class PartListenerManager implements IWindowListener {

  private final IPartListener listener;

  public PartListenerManager(IPartListener partListener) {
    this.listener = partListener;
  }

  @Override
  public void windowActivated(IWorkbenchWindow window) {
    window.getActivePage().addPartListener(listener);
  }

  @Override
  public void windowClosed(IWorkbenchWindow window) {
    IWorkbenchPage activePage = window.getActivePage();
    if (activePage != null) {
      activePage.removePartListener(listener);
    }
  }

  @Override
  public void windowDeactivated(IWorkbenchWindow window) {
    window.getActivePage().removePartListener(listener);
  }

  @Override
  public void windowOpened(IWorkbenchWindow window) {
    // nothing to do
  }

  public void deactivate(IWorkbench workbench) {
    workbench.removeWindowListener(this);
    IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
    if (window != null) {
      IWorkbenchPage activePage = window.getActivePage();
      if (activePage != null) {
        activePage.removePartListener(listener);
      }
    }
  }

  public void activate(IWorkbench workbench) {
    workbench.addWindowListener(this);
    IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
    if (window != null) {
      IWorkbenchPage activePage = window.getActivePage();
      if (activePage != null) {
        activePage.addPartListener(listener);
      }
    }
  }
}