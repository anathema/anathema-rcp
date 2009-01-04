package net.sf.anathema.charms.character.points;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.points.cost.CostDto;
import net.sf.anathema.character.points.cost.CostTagDto;
import net.sf.anathema.charms.tree.ICharmId;

public class CharmCosts implements ICharmCost {

  private final IPredicate<ICharmId> cheapPredicate;
  private final CostDto costs;

  public static CharmCosts From(ICharacterId characterId, IModelCollection modelCollection) {
    CostDto cost = CharmCostExtensionPoint.getCost(characterId);
    IPredicate<ICharmId> cheapPredicate = CheapCharmPredicate.From(modelCollection, characterId);
    return new CharmCosts(cheapPredicate, cost);
  }

  public CharmCosts(IPredicate<ICharmId> cheapPredicate, CostDto costs) {
    this.cheapPredicate = cheapPredicate;
    this.costs = costs;
  }

  @Override
  public int getBonusPointCost(ICharmId charmId) {
    return getCost(costs.bonus, charmId);
  }

  @Override
  public int getExperienceCost(ICharmId charmId) {
    return getCost(costs.experience, charmId);
  }

  private int getCost(CostTagDto costTag, ICharmId charmId) {
    return cheapPredicate.evaluate(charmId) ? costTag.cheap : costTag.expensive;
  }
}