package net.sf.anathema.basics.eclipse.extension;

import java.net.URL;

import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.resource.ImageDescriptor;

public interface IExtensionElement {

  public String getAttribute(String name);

  public <K extends IExecutableExtension> K getAttributeAsObject(String name, Class<K> clazz) throws ExtensionException;

  public ImageDescriptor createImageDescriptorFromAttribute(String name);

  public boolean getBooleanAttribute(String name);

  public int getIntegerAttribute(String name);

  public IExtensionElement getElement(String name);

  public IExtensionElement[] getElements();

  public IExtensionElement[] getElements(String name);

  public String getName();

  public URL getResourceAttribute(String attributeName);

  public boolean hasAttribute(String attributeName);

  public String getContributorId();
}