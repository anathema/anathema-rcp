package net.sf.anathema.rcp;

import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

public class Application implements IApplication {

  @Override
  public Object start(IApplicationContext context) throws Exception {
    boolean lockSucceeded = Platform.getInstanceLocation().lock();
    Display display = PlatformUI.createDisplay();
    if (!lockSucceeded) {
      MessageDialog.openInformation(
          display.getActiveShell(),
          Messages.Application_LockDialogTitle,
          Messages.Application_LockDialogMessage);
      return EXIT_OK;
    }
    try {
      int returnCode = PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());
      if (returnCode == PlatformUI.RETURN_RESTART) {
        return EXIT_RESTART;
      }
      return EXIT_OK;
    }
    finally {
      display.dispose();
      Platform.getInstanceLocation().release();
    }
  }

  @Override
  public void stop() {
    // nothing to do
  }
}