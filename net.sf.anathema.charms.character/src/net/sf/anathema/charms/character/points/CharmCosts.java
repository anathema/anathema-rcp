package net.sf.anathema.charms.character.points;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterType;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.type.CharacterTypeFinder;
import net.sf.anathema.character.points.cost.CostDto;
import net.sf.anathema.character.points.cost.CostTagDto;

public class CharmCosts implements ICharmCost {

  private final IPredicate<String> cheapPredicate;
  private final CostDto costs;

  public static CharmCosts From(ICharacterId characterId, IModelCollection modelCollection) {
    ICharacterType characterType = new CharacterTypeFinder().getCharacterType(characterId);
    CostDto cost = new CharmCostExtensionPoint().getCost(characterType);
    IPredicate<String> cheapPredicate = CheapCharmPredicate.From(modelCollection, characterId);
    return new CharmCosts(cheapPredicate, cost);
  }

  public CharmCosts(IPredicate<String> cheapPredicate, CostDto costs) {
    this.cheapPredicate = cheapPredicate;
    this.costs = costs;
  }

  @Override
  public int getBonusPointCost(String charmId) {
    return getCost(costs.bonus, charmId);
  }

  @Override
  public int getExperienceCost(String charmId) {
    return getCost(costs.experience, charmId);
  }

  private int getCost(CostTagDto costTag, String charmId) {
    return cheapPredicate.evaluate(charmId) ? costTag.cheap : costTag.expensive;
  }
}