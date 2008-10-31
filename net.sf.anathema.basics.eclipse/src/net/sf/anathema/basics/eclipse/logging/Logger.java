package net.sf.anathema.basics.eclipse.logging;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Bundle;

public class Logger implements ILogger {

  private final String bundleName;

  public Logger(String bundleName) {
    this.bundleName = bundleName;
  }

  public final void error(String message, Throwable throwable) {
    getLog().log(createErrorStatus(message, throwable));
  }

  public final ILog getLog() {
    Bundle bundle = Platform.getBundle(bundleName);
    return Platform.getLog(bundle);
  }

  public final IStatus createErrorStatus(String message, Throwable throwable) {
    return createStatus(IStatus.ERROR, message, throwable);
  }

  private IStatus createStatus(int severity, String message, Throwable throwable) {
    return new Status(severity, bundleName, IStatus.OK, message, throwable);
  }

  public void warn(String message, Throwable throwable) {
    getLog().log(createStatus(IStatus.WARNING, message, throwable));
  }
}