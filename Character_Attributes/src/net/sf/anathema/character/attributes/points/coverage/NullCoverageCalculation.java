package net.sf.anathema.character.attributes.points.coverage;

import net.sf.anathema.lib.util.IIdentificate;

public class NullCoverageCalculation implements ICoverageCalculation {

  private final int maximalValue;

  public NullCoverageCalculation(int maximalValue) {
    this.maximalValue = maximalValue;
  }

  @Override
  public int getPointsNotCovered(IIdentificate identificate) {
    return 0;
  }

  @Override
  public int getPointsCovered(IIdentificate identificate) {
    return maximalValue;
  }
}