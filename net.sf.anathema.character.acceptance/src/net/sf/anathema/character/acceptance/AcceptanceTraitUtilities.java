package net.sf.anathema.character.acceptance;

import java.util.List;

import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;

import org.eclipse.core.runtime.AssertionFailedException;

public class AcceptanceTraitUtilities {

  public static IInteractiveTrait extract(List<IDisplayTraitGroup<IInteractiveTrait>> abilities, String traitId) {
    for (IDisplayTraitGroup<IInteractiveTrait> group : abilities) {
      for (IInteractiveTrait trait : group.getTraits()) {
        if (trait.getTraitType().getId().equals(traitId)) {
          return trait;
        }
      }
    }
    throw new AssertionFailedException("Trait not found " + traitId); //$NON-NLS-1$
  }

}
