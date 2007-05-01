package net.sf.anathema.basics.repository;

import net.sf.anathema.basics.repository.initialization.ProjectInitializer;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class RepositoryPlugin extends AbstractUIPlugin {

  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    new ProjectInitializer().initialize();
  }
}