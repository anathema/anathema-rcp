package net.sf.anathema.basics.eclipse;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

public class EclipsePlugin extends Plugin {

  private static final String ID = "net.sf.anathema.basics.eclipse"; //$NON-NLS-1$
  private static EclipsePlugin plugin;

  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
  }

  @Override
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }

  public static void log(int severity, String message, Throwable throwable) {
    plugin.getLog().log(new Status(severity, ID, IStatus.OK, message, throwable));
  }
}