package net.sf.anathema.character.caste.model;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.lib.util.IIdentificate;

public class Caste implements ICaste {

  private final IExtensionElement element;

  public Caste(IExtensionElement element) {
    this.element = element;
  }

  @Override
  public String getId() {
    String id = element.getAttribute("casteId"); //$NON-NLS-1$
    if (id == null) {
      throw new IllegalStateException("Caste id must be given."); //$NON-NLS-1$
    }
    return id;
  }

  @Override
  public String getPrintName() {
    return element.getAttribute("printName"); //$NON-NLS-1$
  }

  @Override
  public boolean supportsTrait(IIdentificate traitType) {
    for (IExtensionElement child : element.getElements()) {
      if (traitType.getId().equals(child.getAttribute("traitId"))) { //$NON-NLS-1$
        return true;
      }
    }
    return false;
  }
}