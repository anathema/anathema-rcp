package net.sf.anathema.character.freebies.abilities;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.abilities.util.TraitListFactory;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.freebies.abilities.util.IAbilityFreebiesConstants;
import net.sf.anathema.character.freebies.configuration.CreditManager;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.points.configuration.AbstractPointHandler;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class UnrestrictedFreebiesBonusPointReducer extends AbstractPointHandler<ITraitCollectionModel> {
  private final ICreditManager creditManager;

  public UnrestrictedFreebiesBonusPointReducer() {
    this(ModelCache.getInstance(), new CreditManager());
  }

  public UnrestrictedFreebiesBonusPointReducer(IModelCollection modelCollection, ICreditManager creditManager) {
    super(modelCollection, IAbilitiesPluginConstants.MODEL_ID);
    this.creditManager = creditManager;
  }

  @Override
  public int calculatePoints(ITraitCollectionModel abilities, ICharacterId characterId) {
    int cheapCredit = creditManager.getCredit(characterId, IAbilityFreebiesConstants.FAVORED_CREDIT);
    int unlimitedCredit = creditManager.getCredit(characterId, IAbilityFreebiesConstants.UNRESTRICTED_CREDIT);
    int availableCheapDots = Math.max(getDotCount(abilities, new CheapPredicate()) - cheapCredit, 0);
    int availableExpensiveDots = getDotCount(abilities, new ExpensivePredicate());
    int expensiveDotsToHandle = Math.min(availableExpensiveDots, unlimitedCredit);
    int cheapDotsToHandle = Math.max(0, Math.min(availableCheapDots, unlimitedCredit - expensiveDotsToHandle));
    return -(expensiveDotsToHandle * 2) - (cheapDotsToHandle * 1);
  }

  private int getDotCount(ITraitCollectionModel traits, IPredicate<IBasicTrait> predicate) {
    int dotCount = 0;
    for (IBasicTrait trait : new TraitListFactory().createFrom(traits)) {
      if (predicate.evaluate(trait)) {
        dotCount += Math.min(trait.getCreationModel().getValue(), 3);
      }
    }
    return dotCount;
  }
}