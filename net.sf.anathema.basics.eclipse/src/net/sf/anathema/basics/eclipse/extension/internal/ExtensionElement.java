package net.sf.anathema.basics.eclipse.extension.internal;

import java.net.URL;

import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.resource.ResourceUtils;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.resource.ImageDescriptor;

public class ExtensionElement implements IExtensionElement {

  public static final class ElementTransformer implements ITransformer<IConfigurationElement, IExtensionElement> {
    @Override
    public IExtensionElement transform(IConfigurationElement element) {
      return new ExtensionElement(element);
    }
  }

  private final IConfigurationElement eclipseElement;

  public ExtensionElement(IConfigurationElement eclipseElement) {
    this.eclipseElement = eclipseElement;
  }

  @Override
  public IExtensionElement getElement(String name) {
    IConfigurationElement[] children = eclipseElement.getChildren(name);
    if (children.length == 0) {
      return NO_ELEMENT;
    }
    return new ExtensionElement(children[0]);
  }

  @Override
  public String getAttribute(String name) {
    return eclipseElement.getAttribute(name);
  }

  public IExtensionElement[] getElements() {
    return ArrayUtilities.transform(
        eclipseElement.getChildren(),
        IExtensionElement.class,
        new ElementTransformer());
  }

  @Override
  public boolean getBooleanAttribute(String name) {
    return Boolean.valueOf(eclipseElement.getAttribute(name));
  }

  @Override
  public ImageDescriptor createImageDescriptorFromAttribute(String name) {
    String resourcePath = eclipseElement.getAttribute(name);
    URL resourceUrl = ResourceUtils.getResourceUrl(getContributorId(), resourcePath);
    return ImageDescriptor.createFromURL(resourceUrl);
  }

  @Override
  public int getIntegerAttribute(String name) {
    return Integer.valueOf(eclipseElement.getAttribute(name));
  }

  public String getContributorId() {
    return eclipseElement.getContributor().getName();
  }

  public boolean hasAttribute(String attributeName) {
    return eclipseElement.getAttribute(attributeName) != null;
  }

  public <K extends IExecutableExtension> K getAttributeAsObject(String name, Class<K> clazz) throws ExtensionException {
    try {
      if (eclipseElement.getAttribute(name) == null) {
        return null;
      }
      return clazz.cast(eclipseElement.createExecutableExtension(name));
    }
    catch (CoreException e) {
      throw new ExtensionException(e);
    }
  }

  @Override
  public String getName() {
    return eclipseElement.getName();
  }

  @Override
  public URL getResourceAttribute(String attributeName) {
    String resourcePath = eclipseElement.getAttribute(attributeName);
    return ResourceUtils.getResourceUrl(eclipseElement.getContributor().getName(), resourcePath);
  }

  @Override
  public IExtensionElement[] getElements(String name) {
    return ArrayUtilities.transform(
        eclipseElement.getChildren(name),
        IExtensionElement.class,
        new ElementTransformer());
  }
}