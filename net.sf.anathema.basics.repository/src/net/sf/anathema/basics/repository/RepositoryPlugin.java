package net.sf.anathema.basics.repository;

import net.sf.anathema.basics.eclipse.plugin.AbstractAnathemaUIPlugin;
import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.basics.repository.initialization.ProjectInitializer;

import org.eclipse.jface.resource.ImageRegistry;
import org.osgi.framework.BundleContext;

public class RepositoryPlugin extends AbstractAnathemaUIPlugin {

  public static final String WARNING_DECORATION = "WARNING_DECORATION"; //$NON-NLS-1$
  public static final String WARNING_ICON = "WARNING"; //$NON-NLS-1$
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
    RepositoryUtilities.refreshWorkspace();
    new ProjectInitializer().initialize();
  }

  @Override
  protected void initializeImageRegistry(ImageRegistry reg) {
    super.initializeImageRegistry(reg);
    reg.put(WARNING_DECORATION, getImageDescriptor("icons/warning_co.gif")); //$NON-NLS-1$
    reg.put(WARNING_ICON, getImageDescriptor("icons/warning.gif").createImage()); //$NON-NLS-1$
  }
}