package net.sf.anathema.editor.styledtext;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class StyledTextPlugin extends AbstractUIPlugin {

  private static final String ID = "net.sf.anathema.editor.styledText"; //$NON-NLS-1$
  private static StyledTextPlugin instance;

  public static void log(int severity, String message, Throwable throwable) {
    instance.getLog().log(new Status(severity, ID, IStatus.OK, message, throwable));
  }

  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    instance = this;
  }

  public static IStatus createErrorStatus(String message, Exception e) {
    return new Status(IStatus.ERROR, ID, IStatus.OK, message, e);
  }
}