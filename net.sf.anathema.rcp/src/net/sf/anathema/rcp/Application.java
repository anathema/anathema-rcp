package net.sf.anathema.rcp;

import static net.sf.anathema.rcp.Messages.*;
import static org.eclipse.jface.dialogs.MessageDialog.*;
import static org.eclipse.ui.PlatformUI.*;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class Application implements IApplication {

  @Override
  public Object start(IApplicationContext context) throws Exception {
    Display display = PlatformUI.createDisplay();
    WorkspaceLock lock = new WorkspaceLock();
    if (!lock.lock()) {
      Shell activeShell = display.getActiveShell();
      openInformation(activeShell, Application_LockDialogTitle, Application_LockDialogMessage);
      return EXIT_OK;
    }
    try {
      int returnCode = createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());
      if (returnCode == RETURN_RESTART) {
        return EXIT_RESTART;
      }
      return EXIT_OK;
    }
    finally {
      lock.release();
      display.dispose();
    }
  }

  @Override
  public void stop() {
    // nothing to do
  }
}