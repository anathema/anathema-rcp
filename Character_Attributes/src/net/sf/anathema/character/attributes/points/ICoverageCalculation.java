package net.sf.anathema.character.attributes.points;

import net.sf.anathema.lib.util.IIdentificate;

public interface ICoverageCalculation {

  int getPointsNotCovered(IIdentificate identificate);
}