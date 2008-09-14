package net.sf.anathema.charms;

import net.sf.anathema.basics.eclipse.extension.IExtensionProvider;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;

public class StaticExtensionProvider implements IExtensionProvider {

  private final IPluginExtension[] extensions;

  public StaticExtensionProvider(IPluginExtension... extensions) {
    this.extensions = extensions;
  }

  @Override
  public IPluginExtension[] getExtensions() {
    return extensions;
  }
}