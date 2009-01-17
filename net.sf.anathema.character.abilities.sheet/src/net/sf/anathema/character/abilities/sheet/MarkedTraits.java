package net.sf.anathema.character.abilities.sheet;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class MarkedTraits {
  private final List<IIdentificate> markedTraitTypes = new ArrayList<IIdentificate>();

  public MarkedTraits() {
    addMarkedTrait("Athletics"); //$NON-NLS-1$
    addMarkedTrait("Dodge"); //$NON-NLS-1$
    addMarkedTrait("Larceny"); //$NON-NLS-1$
    addMarkedTrait("Ride"); //$NON-NLS-1$
    addMarkedTrait("Stealth"); //$NON-NLS-1$
  }

  private void addMarkedTrait(String id) {
    markedTraitTypes.add(new Identificate(id));
  }

  public boolean isMarked(IDisplayTrait trait) {
    return markedTraitTypes.contains(trait.getTraitType());
  }
}