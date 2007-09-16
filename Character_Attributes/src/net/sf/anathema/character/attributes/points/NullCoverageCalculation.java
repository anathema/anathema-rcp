package net.sf.anathema.character.attributes.points;

import net.sf.anathema.lib.util.IIdentificate;

public class NullCoverageCalculation implements ICoverageCalculation {

  @Override
  public int getPointsNotCovered(IIdentificate identificate) {
    return 0;
  }
}