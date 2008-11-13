package net.sf.anathema.charms.character.points;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.charms.character.CharmModelUtilities;
import net.sf.anathema.charms.character.ICharmModel;

public class CharmExperienceExpenditure {

  private final ICharmModel charmModel;
  private final ICharmExperienceCosts costs;

  public CharmExperienceExpenditure(ICharacterId characterId, IModelCollection modelCollection) {
    charmModel = CharmModelUtilities.getModelFor(characterId, modelCollection);
    costs = new CharmExperienceCosts(characterId, modelCollection);
  }

  public int getPoints() {
    int experiencePoints = 0;
    for (String charmId : charmModel.getExperienceLearnedCharms()) {
      experiencePoints += costs.getFor(charmId);
    }
    return experiencePoints;
  }
}