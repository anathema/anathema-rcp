package net.sf.anathema.character.attributes;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class AttributesPlugin extends AbstractUIPlugin {

  public static final String PLUGIN_ID = "net.sf.anathema.character.attributes"; //$NON-NLS-1$
  private static AttributesPlugin plugin;

  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
  }

  @Override
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }

  public static AttributesPlugin getDefault() {
    return plugin;
  }
}