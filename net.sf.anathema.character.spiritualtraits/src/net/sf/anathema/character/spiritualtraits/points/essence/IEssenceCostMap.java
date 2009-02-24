package net.sf.anathema.character.spiritualtraits.points.essence;

public interface IEssenceCostMap {

  public int getEssenceBonuspointCost(String characterType);

  public int getEssenceExperienceCost(String characterType);
}