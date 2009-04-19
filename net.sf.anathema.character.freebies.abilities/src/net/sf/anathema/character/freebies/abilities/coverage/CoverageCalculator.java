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
    ICoverageBuilder coverageBuilder = createCoverageBuilder(characterId, experience);
    for (IBasicTrait trait : collection.getAllTraits()) {
      int currentCoverage = coverageBuilder.calculateCoverageForNextTrait(trait);
      if (trait.getTraitType().equals(traitType)) {
        return currentCoverage;
      }
    }
    throw new UnreachableCodeReachedException();
  }

  private ICoverageBuilder createCoverageBuilder(ICharacterId characterId, IExperience experience) {
    return experience.isExperienced() ? new ExperienceCoverageBuilder() : createBonusPointCoverageBuilder(characterId);
  }

  private ICoverageBuilder createBonusPointCoverageBuilder(ICharacterId characterId) {
    int remainingCheapFreebies = creditManager.getCredit(characterId, IAbilityFreebiesConstants.FAVORED_CREDIT);
    int remainingExpensiveFreebies = creditManager.getCredit(characterId, IAbilityFreebiesConstants.UNRESTRICTED_CREDIT);
    return new BonuspointCoverageBuilder(remainingCheapFreebies, remainingExpensiveFreebies);
  }
}