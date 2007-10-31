package net.sf.anathema.character.experience.internal;

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
    IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
    if (window != null) {
      window.getActivePage().removePartListener(listener);
    }
  }
}