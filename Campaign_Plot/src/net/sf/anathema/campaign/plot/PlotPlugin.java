package net.sf.anathema.campaign.plot;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class PlotPlugin extends AbstractUIPlugin {


  private static final String ID = "net.sf.anathema.basics.repository"; //$NON-NLS-1$
  private static PlotPlugin instance;

  public static void log(int severity, String message, Throwable throwable) {
    instance.getLog().log(new Status(severity, ID, IStatus.OK, message, throwable));
  }

  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    instance = this;
  }
}