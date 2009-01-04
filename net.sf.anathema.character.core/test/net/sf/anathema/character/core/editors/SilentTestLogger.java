package net.sf.anathema.character.core.editors;

import net.sf.anathema.basics.eclipse.logging.ILogger;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;

public class SilentTestLogger implements ILogger {

  private Throwable throwable;
  
  @Override
  public IStatus createErrorStatus(String message, Throwable newThrowable) {
    this.throwable = newThrowable;
    return null;
  }

  @Override
  public void error(String message, Throwable newThrowable) {
    this.throwable = newThrowable;
  }

  @Override
  public ILog getLog() {
    return null;
  }
  
  public void verify() throws Throwable {
    if (throwable != null) {
      throw throwable;
    }
  }
}
