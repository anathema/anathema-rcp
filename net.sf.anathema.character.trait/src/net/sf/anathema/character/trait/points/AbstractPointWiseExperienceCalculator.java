package net.sf.anathema.character.trait.points;

import net.sf.anathema.character.trait.IBasicTrait;

public abstract class AbstractPointWiseExperienceCalculator {

  private final int xpCost;

  public AbstractPointWiseExperienceCalculator(int xpCost) {
    this.xpCost = xpCost;
  }

  public final int calculate() {
    int points = 0;
    for (IBasicTrait trait : getCalculationTraits()) {
      points += calculatePointsForTrait(trait);
    }
    return points;
  }

  private int calculatePointsForTrait(IBasicTrait trait) {
    int creationValue = trait.getCreationModel().getValue();
    int experienceValue = trait.getExperiencedModel().getValue();
    return calculateXp(creationValue, experienceValue);
  }

  private int calculateXp(int creationValue, int experienceValue) {
    int xpSpent = 0;
    for (int currentValue = creationValue; currentValue < experienceValue; currentValue++) {
      xpSpent += currentValue * xpCost;
    }
    return xpSpent;
  }

  protected abstract IBasicTrait[] getCalculationTraits();
}