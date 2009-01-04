package net.sf.anathema.charms.character.points;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.charms.character.CharmModelUtilities;
import net.sf.anathema.charms.character.model.ICharmModel;
import net.sf.anathema.charms.tree.ICharmId;

public class CharmExperienceCosts implements ICharmExperienceCosts {

  private final ICharmCost charmCost;
  private final ICharmModel charmModel;

  public CharmExperienceCosts(ICharacterId characterId, IModelCollection modelCollection) {
    charmCost = CharmCosts.From(characterId, modelCollection);
    charmModel = CharmModelUtilities.getModelFor(characterId, modelCollection);
  }

  public int getFor(ICharmId charmId) {
    if (charmModel.isCreationLearned(charmId)) {
      return 0;
    }
    return charmCost.getExperienceCost(charmId);
  }
}