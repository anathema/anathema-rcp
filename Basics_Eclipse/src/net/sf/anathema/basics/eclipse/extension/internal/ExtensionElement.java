package net.sf.anathema.basics.eclipse.extension.internal;

import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;

public class ExtensionElement implements IExtensionElement {

  private final IConfigurationElement eclipseElement;

  public ExtensionElement(IConfigurationElement elipseElement) {
    this.eclipseElement = elipseElement;
  }

  @Override
  public IExtensionElement getElement(String name) {
    IConfigurationElement[] children = eclipseElement.getChildren(name);
    if (children.length == 0) {
      return null;
    }
    return new ExtensionElement(children[0]);
  }

  @Override
  public String getAttribute(String name) {
    return eclipseElement.getAttribute(name);
  }

  @Override
  public boolean getBooleanAttribute(String name) {
    return Boolean.valueOf(eclipseElement.getAttribute(name));
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
}