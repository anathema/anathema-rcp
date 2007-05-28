package net.sf.anathema.basics.eclipse.plugin;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public abstract class AbstractAnathemaUIPlugin extends AbstractUIPlugin {

  public void log(int severity, String message, Throwable throwable) {
    AbstractAnathemaUIPlugin pluginInstance = getPluginInstance();
    String symbolicName = pluginInstance.getBundle().getSymbolicName();
    pluginInstance.getLog().log(new Status(severity, symbolicName, IStatus.OK, message, throwable));
  }
  
  protected abstract AbstractAnathemaUIPlugin getPluginInstance();
}