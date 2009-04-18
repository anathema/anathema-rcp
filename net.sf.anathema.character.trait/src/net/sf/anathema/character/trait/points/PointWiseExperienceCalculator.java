package net.sf.anathema.character.trait.points;

import net.sf.anathema.character.trait.IBasicTrait;

public class PointWiseExperienceCalculator extends AbstractExperienceCalculator {

  private final int xpCost;

  public PointWiseExperienceCalculator(int xpCost, Iterable<IBasicTrait> traits) {
    super(traits);
    this.xpCost = xpCost;
  }

  @Override
  protected int calculateXp(int creationValue, int experienceValue) {
    int increment = Math.max(0, experienceValue - creationValue);
    return increment * xpCost;
  }
}