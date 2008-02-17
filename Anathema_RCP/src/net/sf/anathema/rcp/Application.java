package net.sf.anathema.rcp;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

public class Application implements IApplication {

  @Override
  public Object start(IApplicationContext context) throws Exception {
    Display display = PlatformUI.createDisplay();
    // TODO Case 139: Sperre den Workspace auch richtig bei neuem Anlegen
//    WorkspaceLock lock = new WorkspaceLock();
//    if (!lock.lock()) {
//      MessageDialog.openInformation(
//          display.getActiveShell(),
//          Messages.Application_LockDialogTitle,
//          Messages.Application_LockDialogMessage);
//      return EXIT_OK;
//    }
    try {
      int returnCode = PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());
      if (returnCode == PlatformUI.RETURN_RESTART) {
        return EXIT_RESTART;
      }
      return EXIT_OK;
    }
    finally {
      display.dispose();
//      lock.release();
    }
  }

  @Override
  public void stop() {
    // nothing to do
  }
}