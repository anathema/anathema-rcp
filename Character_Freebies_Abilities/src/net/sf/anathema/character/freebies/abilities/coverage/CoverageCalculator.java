package net.sf.anathema.character.freebies.abilities.coverage;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.freebies.abilities.util.IAbilityFreebiesConstants;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.lib.util.IIdentificate;

public class CoverageCalculator {

  private final ICreditManager creditManager;

  // TODO: Case 191: Komplette Behandlung für Bonuspunktausgaben
  public CoverageCalculator(ICreditManager creditManager) {
    this.creditManager = creditManager;
  }

  public int calculate(ITraitCollectionModel collection, ICharacterId characterId, IIdentificate traitType) {
    int remainingCheapFreebies = creditManager.getCredit(characterId, IAbilityFreebiesConstants.FAVORED_CREDIT);
    int remainingExpensiveFreebies = creditManager.getCredit(characterId, IAbilityFreebiesConstants.UNLIMITED_CREDIT);
    for (IBasicTrait trait : collection.getTraits()) {
      int possiblyCovered = Math.min(trait.getCreationModel().getValue(), 3);
      int cheapFreebiesSpent = 0;
      if (trait.getStatusManager().getStatus().isCheap()) {
        cheapFreebiesSpent = Math.min(remainingCheapFreebies, possiblyCovered);
      }
      int expensiveFreebiesSpent = Math.min(remainingExpensiveFreebies, possiblyCovered - cheapFreebiesSpent);
      remainingCheapFreebies -= cheapFreebiesSpent;
      remainingExpensiveFreebies -= expensiveFreebiesSpent;
      if (trait.getTraitType().equals(traitType)) {
        return cheapFreebiesSpent + expensiveFreebiesSpent;
      }
    }
    throw new UnreachableCodeReachedException();
  }
}
