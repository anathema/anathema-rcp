package net.sf.anathema.character.caste.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.character.caste.ICaste;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class Caste implements ICaste {

  private static final String ATTRIB_TRAIT_ID = "traitId"; //$NON-NLS-1$
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
      if (traitType.getId().equals(child.getAttribute(ATTRIB_TRAIT_ID))) {
        return true;
      }
    }
    return false;
  }

  @Override
  public List< ? extends IIdentificate> getTraitTypes() {
    List<IIdentificate> traitIds = new ArrayList<IIdentificate>();
    for (IExtensionElement child : element.getElements()) {
      traitIds.add(new Identificate(child.getAttribute(ATTRIB_TRAIT_ID)));
    }
    return traitIds;
  }
}