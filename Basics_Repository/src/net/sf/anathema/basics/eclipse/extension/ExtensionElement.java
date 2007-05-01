package net.sf.anathema.basics.eclipse.extension;

import org.eclipse.core.runtime.IConfigurationElement;

public class ExtensionElement implements IExtensionElement {

  private final IConfigurationElement elipseElement;

  public ExtensionElement(IConfigurationElement elipseElement) {
    this.elipseElement = elipseElement;
  }
  
  @Override
  public String getAttribute(String name) {
    return elipseElement.getAttribute(name);
  }
}