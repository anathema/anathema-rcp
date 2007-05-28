package net.sf.anathema.character.core;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class CharacterCorePlugin extends Plugin {

  public static final String PLUGIN_ID = "net.sf.anathema.character.core"; //$NON-NLS-1$
  private static CharacterCorePlugin plugin;

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

  public static CharacterCorePlugin getDefault() {
    return plugin;
  }
}