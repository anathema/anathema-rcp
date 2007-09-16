package net.sf.anathema.character.attributes.points;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.lib.util.IIdentificate;

public class CoverageCalculation implements ICoverageCalculation {

  private final Map<String, Integer> results = new HashMap<String, Integer>();

  @Override
  public int getPointsNotCovered(IIdentificate identificate) {
    return results.get(identificate.getId());
  }

  public void addResult(String type, int result) {
    results.put(type, Math.max(0, result));
  }
}