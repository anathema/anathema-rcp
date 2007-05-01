package net.sf.anathema.basics.repository;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.basics.repository.initialization.ProjectInitializer;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class RepositoryPlugin extends AbstractUIPlugin {

  private static final String ID = "net.sf.anathema.basics.repository"; //$NON-NLS-1$
  private static RepositoryPlugin instance;

  public RepositoryPlugin() {
    Ensure.ensureNull(instance);
    instance = this;
  }

  public static void log(int severity, String message, Throwable throwable) {
    instance.getLog().log(new Status(severity, ID, IStatus.OK, message, throwable));
  }

  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    new ProjectInitializer().initialize();
  }
}