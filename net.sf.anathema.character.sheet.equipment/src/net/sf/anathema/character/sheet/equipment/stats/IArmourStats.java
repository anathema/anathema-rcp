package net.sf.anathema.character.sheet.equipment.stats;

public interface IArmourStats extends IDefensiveStats {

  public Integer getHardness(HealthType type);

  public Integer getSoak(HealthType type);
}