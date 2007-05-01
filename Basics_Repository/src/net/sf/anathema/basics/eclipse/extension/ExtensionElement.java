package net.sf.anathema.basics.eclipse.extension;

import org.eclipse.core.runtime.IConfigurationElement;

public class ExtensionElement implements IExtensionElement {

  private final IConfigurationElement eclipseElement;

  public ExtensionElement(IConfigurationElement elipseElement) {
    this.eclipseElement = elipseElement;
  }
  
  @Override
  public String getAttribute(String name) {
    return eclipseElement.getAttribute(name);
  }
}