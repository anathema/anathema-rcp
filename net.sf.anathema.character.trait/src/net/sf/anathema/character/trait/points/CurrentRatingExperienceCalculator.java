package net.sf.anathema.character.trait.points;

import net.sf.anathema.character.trait.IBasicTrait;

public class CurrentRatingExperienceCalculator extends AbstractExperienceCalculator {

  private final int xpCost;

  public CurrentRatingExperienceCalculator(final int xpCost, final IBasicTrait... traits) {
    super(traits);
    this.xpCost = xpCost;
  }

  @Override
  protected int calculateXp(final int creationValue, final int experienceValue) {
    int xpSpent = 0;
    for (int currentValue = creationValue; currentValue < experienceValue; currentValue++) {
      xpSpent += currentValue * xpCost;
    }
    return xpSpent;
  }
}