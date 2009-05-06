package net.sf.anathema.character.sheet.health;

public interface IHealthyCharacter {

  public int getMoveValue(HealthLevelType injuryLevel);

  public int getDashValue(HealthLevelType injuryLevel);

  public int getVerticalJump(HealthLevelType level);

  public int getDyingLevels();

  public int getHealthLevelCount(HealthLevelType level);

  public boolean hasPainTolerance();

  public int getPenalty(HealthLevelType level);
}