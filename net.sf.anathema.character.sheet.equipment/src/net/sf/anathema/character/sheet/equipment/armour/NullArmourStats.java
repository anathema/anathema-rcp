package net.sf.anathema.character.sheet.equipment.armour;

import net.sf.anathema.character.sheet.equipment.stats.HealthType;
import net.sf.anathema.character.sheet.equipment.stats.IArmourStats;

public class NullArmourStats implements IArmourStats {

  @Override
  public Integer getHardness(HealthType type) {
    return null;
  }

  @Override
  public Integer getSoak(HealthType type) {
    return null;
  }

  @Override
  public Integer getFatigue() {
    return null;
  }

  @Override
  public Integer getMobilityPenalty() {
    return null;
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public String getId() {
    return null;
  }
}