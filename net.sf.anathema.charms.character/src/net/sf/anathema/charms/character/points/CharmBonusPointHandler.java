package net.sf.anathema.charms.character.points;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.points.configuration.IPointHandler;
import net.sf.anathema.charms.character.model.CharmModel;
import net.sf.anathema.charms.character.model.ICharmModel;

public class CharmBonusPointHandler extends AbstractExecutableExtension implements IPointHandler {

  private final IModelCollection modelCollection;
  private final ICharmCostFactory charmCostFactory;

  public CharmBonusPointHandler() {
    this(ModelCache.getInstance(), new CharmCostFactory(ModelCache.getInstance()));
  }

  public CharmBonusPointHandler(IModelCollection modelCollection, ICharmCostFactory cheapPredicateFactory) {
    this.modelCollection = modelCollection;
    charmCostFactory = cheapPredicateFactory;
  }

  @Override
  public int getPoints(ICharacterId characterId) {
    ICharmCost charmCost = charmCostFactory.create(characterId);
    ICharmModel charmModel = CharmModel.getFrom(modelCollection, characterId);
    return getCostsForCharms(charmModel, charmCost);
  }

  private int getCostsForCharms(ICharmModel charmModel, ICharmCost charmCost) {
    int bonusPointSum = 0;
    for (String charmId : charmModel.getCreationLearnedCharms()) {
      bonusPointSum += charmCost.getBonusPointCost(charmId);
    }
    return bonusPointSum;
  }
}