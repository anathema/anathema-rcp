package net.sf.anathema.character.attributes.points;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class PointCoverageCalculator {

  private final ITraitCollectionContext context;
  private final int credit;
  private final Map<IIdentificate, Integer> results = new HashMap<IIdentificate, Integer>();

  public PointCoverageCalculator(ITraitCollectionContext context, int credit) {
    this.context = context;
    this.credit = credit;
  }

  public int pointCoverage(Identificate traitType) {
    return results.get(traitType);
  }

  public void calculateFor(IIdentificate... traitTypes) {
    if (context.getExperience().isExperienced()) {
      for (IIdentificate type : traitTypes) {
        results.put(type, 0);
      }
      return;
    }
    ITraitCollectionModel collection = context.getCollection();
    int creditLeft = credit;
    for (IIdentificate type : traitTypes) {
      int value = collection.getTrait(type.getId()).getCreationModel().getValue();
      int result = value - creditLeft;
      creditLeft = Math.max(creditLeft - value, 0);
      results.put(type, result);
    }
  }
}