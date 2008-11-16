package net.sf.anathema.charms.character.points;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.charms.character.CharmModelUtilities;
import net.sf.anathema.charms.character.ICharmModel;

public class CharmExperienceCosts implements ICharmExperienceCosts {

  private final IPredicate<String> cheapPredicate;
  private final ICharmModel charmModel;

  public CharmExperienceCosts(ICharacterId characterId, IModelCollection modelCollection) {
    cheapPredicate = CheapCharmPredicate.From(modelCollection, characterId);
    charmModel = CharmModelUtilities.getModelFor(characterId, modelCollection);
  }

  public int getFor(String charmId) {
    if (charmModel.isCreationLearned(charmId)) {
      return 0;
    }
    return cheapPredicate.evaluate(charmId) ? getCheapCost() : getExpensiveCost();
  }

  private int getExpensiveCost() {
    return 10;
  }

  private int getCheapCost() {
    return 8;
  }
}