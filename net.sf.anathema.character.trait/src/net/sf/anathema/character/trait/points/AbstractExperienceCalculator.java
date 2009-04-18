package net.sf.anathema.character.trait.points;

import java.util.Arrays;

import net.sf.anathema.character.trait.IBasicTrait;

public abstract class AbstractExperienceCalculator implements ICalculator {

  private final Iterable<IBasicTrait> traits;

  public AbstractExperienceCalculator(final IBasicTrait... traits) {
    this(Arrays.asList(traits));
  }

  public AbstractExperienceCalculator(final Iterable<IBasicTrait> traits) {
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

  protected abstract int calculateXp(final int creationValue, final int experienceValue);
}