package net.sf.anathema.character.freebies.abilities.coverage;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.freebies.abilities.util.IAbilityFreebiesConstants;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.lib.util.IIdentificate;

public class CoverageCalculator {

  private final ICreditManager creditManager;
  private final IModelCollection modelCollection;

  public CoverageCalculator(ICreditManager creditManager, IModelCollection modelCollection) {
    this.creditManager = creditManager;
    this.modelCollection = modelCollection;
  }

  public int calculate(ITraitCollectionModel collection, ICharacterId characterId, IIdentificate traitType) {
    ModelIdentifier experienceIdentifier = new ModelIdentifier(characterId, IExperience.MODEL_ID);
    IExperience experience = (IExperience) modelCollection.getModel(experienceIdentifier);
    if (experience.isExperienced()) {
      IBasicTrait trait = collection.getTrait(traitType.getId());
      return trait.getCreationModel().getValue();
    }
    int remainingCheapFreebies = creditManager.getCredit(characterId, IAbilityFreebiesConstants.FAVORED_CREDIT);
    int remainingExpensiveFreebies = creditManager.getCredit(characterId, IAbilityFreebiesConstants.UNRESTRICTED_CREDIT);
    for (IBasicTrait trait : collection.getAllTraits()) {
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