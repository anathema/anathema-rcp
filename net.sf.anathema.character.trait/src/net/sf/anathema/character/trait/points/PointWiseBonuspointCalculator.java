package net.sf.anathema.character.trait.points;

import net.sf.anathema.character.trait.IBasicTrait;

public class PointWiseBonuspointCalculator extends AbstractPointWiseBonuspointCalculator {

  private final IBasicTrait[] traits;

  public PointWiseBonuspointCalculator(final PointwiseCostDto pointDto, final IBasicTrait... traits) {
    super(pointDto);
    this.traits = traits;
  }

  @Override
  protected IBasicTrait[] getCalculationTraits() {
    return traits;
  }
}