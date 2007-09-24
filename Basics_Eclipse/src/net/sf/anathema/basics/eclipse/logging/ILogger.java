package net.sf.anathema.basics.eclipse.logging;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;

public interface ILogger {

  public void error(String message, Throwable throwable);

  public ILog getLog();

  public IStatus createErrorStatus(String message, Throwable throwable);

}