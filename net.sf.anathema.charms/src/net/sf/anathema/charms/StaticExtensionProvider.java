package net.sf.anathema.charms;

import net.sf.anathema.basics.eclipse.extension.AbstractExtensionProvider;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;

public class StaticExtensionProvider extends AbstractExtensionProvider {

  private final IPluginExtension[] extensions;

  public StaticExtensionProvider(IPluginExtension... extensions) {
    this.extensions = extensions;
  }

  @Override
  public IPluginExtension[] getExtensions() {
    return extensions;
  }
}