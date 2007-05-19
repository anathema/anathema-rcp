package net.sf.anathema.basics.eclipse.extension;

public interface IExtensionProvider {

  public IPluginExtension[] getExtensions(String id);
}