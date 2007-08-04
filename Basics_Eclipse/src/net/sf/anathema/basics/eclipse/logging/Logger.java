package net.sf.anathema.basics.eclipse.logging;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Bundle;

public class Logger {

  private final String bundleName;
  
  public Logger(String bundleName) {
    this.bundleName = bundleName;
  }

  public final void log(int severity, String message, Throwable throwable) {
    getLog().log(new Status(severity, bundleName, IStatus.OK, message, throwable));
  }

  public final ILog getLog() {
    Bundle bundle = Platform.getBundle(bundleName);
    return Platform.getLog(bundle);
  }

  public final IStatus createErrorStatus(String message, Exception e) {
    return new Status(IStatus.ERROR, bundleName, IStatus.OK, message, e);
  }
}