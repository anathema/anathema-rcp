package net.sf.anathema.basics.eclipse.extension.fake;

import net.sf.anathema.basics.eclipse.extension.AbstractExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;

public final class DummyExtensionPointExtension extends AbstractExtensionPoint {
  private final IPluginExtension[] pluginExtensions;

  public DummyExtensionPointExtension(IPluginExtension... pluginExtensions) {
    this.pluginExtensions = pluginExtensions;
  }

  @Override
  public IPluginExtension[] getExtensions() {
    return pluginExtensions;
  }
}