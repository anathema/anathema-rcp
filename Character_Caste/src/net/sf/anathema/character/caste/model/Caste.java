package net.sf.anathema.character.caste.model;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

import org.eclipse.jface.resource.ImageDescriptor;

public class Caste implements ICaste {

  private final IExtensionElement element;

  public Caste(IExtensionElement element) {
    this.element = element;
  }

  public String getId() {
    String id = element.getAttribute("casteId"); //$NON-NLS-1$
    if (id == null) {
      throw new IllegalStateException("Caste id must be given."); //$NON-NLS-1$
    }
    return id;
  }

  public String getPrintName() {
    return element.getAttribute("printName"); //$NON-NLS-1$
  }

  public ImageDescriptor getIcon() {
    return element.createImageDescriptorFromAttribute("icon"); //$NON-NLS-1$
  }
}