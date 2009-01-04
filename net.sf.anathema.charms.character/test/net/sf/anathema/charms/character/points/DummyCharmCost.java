package net.sf.anathema.charms.character.points;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.charms.tree.ICharmId;

public class DummyCharmCost implements ICharmCost {

  private final int defaultBonus;
  private final int defaultExperience;
  private final Map<ICharmId, Integer> bonusCostByCharmId = new HashMap<ICharmId, Integer>();
  private final Map<ICharmId, Integer> experienceCostByCharmId = new HashMap<ICharmId, Integer>();

  public DummyCharmCost(int defaultBonus, int defaultExperience) {
    this.defaultBonus = defaultBonus;
    this.defaultExperience = defaultExperience;
  }

  @Override
  public int getBonusPointCost(ICharmId charmId) {
    if (bonusCostByCharmId.containsKey(charmId)) {
      return bonusCostByCharmId.get(charmId);
    }
    return defaultBonus;
  }

  public void setBonusCost(ICharmId charmId, int cost) {
    bonusCostByCharmId.put(charmId, cost);
  }

  @Override
  public int getExperienceCost(ICharmId charmId) {
    if (experienceCostByCharmId.containsKey(charmId)) {
      return experienceCostByCharmId.get(charmId);
    }
    return defaultExperience;
  }

  public void setExperienceCost(ICharmId charmId, int cost) {
    experienceCostByCharmId.put(charmId, cost);
  }
}