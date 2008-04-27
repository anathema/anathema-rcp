package net.sf.anathema.character.abilities.sheet;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class MarkedTraits {
  private final List<IIdentificate> markedTraitTypes = new ArrayList<IIdentificate>();

  public MarkedTraits() {
    markedTraitTypes.add(new Identificate("Athletics")); //$NON-NLS-1$
    markedTraitTypes.add(new Identificate("Dodge")); //$NON-NLS-1$
    markedTraitTypes.add(new Identificate("Larceny")); //$NON-NLS-1$
    markedTraitTypes.add(new Identificate("Ride")); //$NON-NLS-1$
    markedTraitTypes.add(new Identificate("Stealth")); //$NON-NLS-1$
  }

  public boolean isMarked(IDisplayTrait trait) {
    return markedTraitTypes.contains(trait.getTraitType());
  }
}