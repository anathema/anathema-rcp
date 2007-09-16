package net.sf.anathema.character.attributes.points.coverage;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.lib.util.IIdentificate;

public class CoverageCalculation implements ICoverageCalculation {

  private final Map<String, Integer> results = new HashMap<String, Integer>();
  private final ITraitCollectionContext context;

  public CoverageCalculation(ITraitCollectionContext context) {
    this.context = context;
  }

  @Override
  public int getPointsNotCovered(IIdentificate identificate) {
    return results.get(identificate.getId());
  }

  public void addResult(String type, int result) {
    results.put(type, Math.max(0, result));
  }

  @Override
  public int getPointsCovered(IIdentificate identificate) {
    return context.getCollection().getTrait(identificate.getId()).getCreationModel().getValue()
        - getPointsNotCovered(identificate);
  }
}