package net.sf.anathema.basics.eclipse.extension;

import org.eclipse.core.runtime.IExecutableExtension;

public interface IExtensionElement {

  public String getAttribute(String name);

  public <K extends IExecutableExtension> K getAttributeAsObject(String name, Class<K> clazz) throws ExtensionException;

  public boolean getBooleanAttribute(String name);

  public IExtensionElement getElement(String name);
}