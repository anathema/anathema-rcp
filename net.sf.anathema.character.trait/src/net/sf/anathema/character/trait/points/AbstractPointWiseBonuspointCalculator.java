package net.sf.anathema.character.trait.points;

import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.lib.lang.ICalculator;

public abstract class AbstractPointWiseBonuspointCalculator implements ICalculator {

  private final PointwiseCostDto pointDto;

  public AbstractPointWiseBonuspointCalculator(PointwiseCostDto pointDto) {
    this.pointDto = pointDto;
  }

  public final int calculate() {
    int points = 0;
    for (IBasicTrait trait : getCalculationTraits()) {
      points += calculatePointsForTrait(trait);
    }
    return points;
  }

  private int calculatePointsForTrait(IBasicTrait trait) {
    int traitValue = trait.getCreationModel().getValue();
    int increment = Math.max(traitValue - pointDto.startValue, 0);
    return increment * pointDto.pointCost;
  }

  protected abstract IBasicTrait[] getCalculationTraits();
}