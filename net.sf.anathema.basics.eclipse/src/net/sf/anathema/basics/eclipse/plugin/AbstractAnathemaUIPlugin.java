package net.sf.anathema.basics.eclipse.plugin;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public abstract class AbstractAnathemaUIPlugin extends AbstractUIPlugin {

  public final IStatus createErrorStatus(String message, Exception e) {
    return new Status(IStatus.ERROR, getSymbolicName(), IStatus.OK, message, e);
  }

  protected abstract void createInstance();

  protected abstract void deleteInstance();

  protected abstract AbstractAnathemaUIPlugin getPluginInstance();

  private String getSymbolicName() {
    AbstractAnathemaUIPlugin pluginInstance = getPluginInstance();
    return pluginInstance.getBundle().getSymbolicName();
  }

  public final void log(int severity, String message, Throwable throwable) {
    AbstractAnathemaUIPlugin pluginInstance = getPluginInstance();
    pluginInstance.getLog().log(new Status(severity, getSymbolicName(), IStatus.OK, message, throwable));
  }

  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    createInstance();
  }

  @Override
  public void stop(BundleContext context) throws Exception {
    deleteInstance();
    super.stop(context);
  }

  public ImageDescriptor getImageDescriptor(String path) {
    return imageDescriptorFromPlugin(getSymbolicName(), path);
  }
}