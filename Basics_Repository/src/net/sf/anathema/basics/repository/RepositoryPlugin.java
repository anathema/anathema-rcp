package net.sf.anathema.basics.repository;

import net.sf.anathema.basics.eclipse.plugin.AbstractAnathemaUIPlugin;
import net.sf.anathema.basics.repository.initialization.ProjectInitializer;

import org.osgi.framework.BundleContext;

public class RepositoryPlugin extends AbstractAnathemaUIPlugin {

  public static final String ID = "net.sf.anathema.basics.repository"; //$NON-NLS-1$
  private static AbstractAnathemaUIPlugin instance;


  public static AbstractAnathemaUIPlugin getDefaultInstance() {
    return instance;
  }

  @Override
  protected final void createInstance() {
    instance = this;
  }

  @Override
  protected final void deleteInstance() {
    instance = null;
  }
  
  @Override
  protected AbstractAnathemaUIPlugin getPluginInstance() {
    return getDefaultInstance();
  }
  
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    new ProjectInitializer().initialize();
  }
}