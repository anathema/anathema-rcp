package net.sf.anathema.character.trait.points;

import net.sf.anathema.character.trait.IBasicTrait;

public class PointWiseExperienceCalculator implements ICalculator {

  private final int xpCost;
  private final IBasicTrait[] traits;

  public PointWiseExperienceCalculator(final int xpCost, final IBasicTrait... traits) {
    this.xpCost = xpCost;
    this.traits = traits;
  }

  public final int calculate() {
    int points = 0;
    for (final IBasicTrait trait : traits) {
      points += calculatePointsForTrait(trait);
    }
    return points;
  }

  private int calculatePointsForTrait(final IBasicTrait trait) {
    final int creationValue = trait.getCreationModel().getValue();
    final int experienceValue = trait.getExperiencedModel().getValue();
    return calculateXp(creationValue, experienceValue);
  }

  private int calculateXp(final int creationValue, final int experienceValue) {
    int xpSpent = 0;
    for (int currentValue = creationValue; currentValue < experienceValue; currentValue++) {
      xpSpent += currentValue * xpCost;
    }
    return xpSpent;
  }
}