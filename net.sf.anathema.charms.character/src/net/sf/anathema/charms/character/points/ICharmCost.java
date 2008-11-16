package net.sf.anathema.charms.character.points;

public interface ICharmCost {

  public int getExperienceCost(String charmId);

  public int getBonusPointCost(String charmId);
}