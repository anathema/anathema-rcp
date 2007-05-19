package net.sf.anathema.basics.eclipse.extension;

public interface IPluginExtension {

  public IExtensionElement[] getElements();

  public String getContributorId();
}