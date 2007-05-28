package net.sf.anathema.basics.repository;

import net.sf.anathema.basics.eclipse.plugin.AbstractAnathemaUIPlugin;
import net.sf.anathema.basics.repository.initialization.ProjectInitializer;

import org.osgi.framework.BundleContext;

public class RepositoryPlugin extends AbstractAnathemaUIPlugin {

  public static final String ID = "net.sf.anathema.basics.repository"; //$NON-NLS-1$
  private static RepositoryPlugin instance;

  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    instance = this;
    new ProjectInitializer().initialize();
  }
  
  public static RepositoryPlugin getDefaultInstance() {
    return instance;
  }

  @Override
  protected AbstractAnathemaUIPlugin getPluginInstance() {
    return instance;
  }
}