package net.sf.anathema.character.freebies.attributes.coverage;

import net.sf.anathema.lib.util.IIdentificate;

public interface ICoverageCalculation {

  int getPointsNotCovered(IIdentificate identificate);

  int getPointsCovered(IIdentificate identificate);
}