package net.sf.anathema.map;

import net.sf.anathema.basics.eclipse.plugin.AbstractAnathemaUIPlugin;

import org.osgi.framework.BundleContext;

public class MapPlugin extends AbstractAnathemaUIPlugin {

  private static MapPlugin instance;

  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    new GisTermInitializer().initialize();
  }

  @Override
  protected void createInstance() {
    instance = this;
  }

  @Override
  protected void deleteInstance() {
    instance = null;
  }

  @Override
  protected AbstractAnathemaUIPlugin getPluginInstance() {
    return instance;
  }
}