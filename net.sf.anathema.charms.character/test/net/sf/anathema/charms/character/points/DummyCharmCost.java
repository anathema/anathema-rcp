package net.sf.anathema.charms.character.points;

import java.util.HashMap;
import java.util.Map;

public class DummyCharmCost implements ICharmCost {

  private final int defaultBonus;
  private final int defaultExperience;
  private final Map<String, Integer> bonusCostByCharmId = new HashMap<String, Integer>();
  private final Map<String, Integer> experienceCostByCharmId = new HashMap<String, Integer>();

  public DummyCharmCost(int defaultBonus, int defaultExperience) {
    this.defaultBonus = defaultBonus;
    this.defaultExperience = defaultExperience;
  }

  @Override
  public int getBonusPointCost(String charmId) {
    if (bonusCostByCharmId.containsKey(charmId)) {
      return bonusCostByCharmId.get(charmId);
    }
    return defaultBonus;
  }

  public void setBonusCost(String charmId, int cost) {
    bonusCostByCharmId.put(charmId, cost);
  }

  @Override
  public int getExperienceCost(String charmId) {
    if (experienceCostByCharmId.containsKey(charmId)) {
      return experienceCostByCharmId.get(charmId);
    }
    return defaultExperience;
  }

  public void setExperienceCost(String charmId, int cost) {
    experienceCostByCharmId.put(charmId, cost);
  }
}