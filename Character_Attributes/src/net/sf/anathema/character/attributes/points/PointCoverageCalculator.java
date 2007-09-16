package net.sf.anathema.character.attributes.points;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.ITraitGroup;
import net.sf.anathema.lib.util.IIdentificate;

public class PointCoverageCalculator {

  private final ITraitCollectionContext context;
  private final int credit;
  private final Map<String, Integer> results = new HashMap<String, Integer>();

  public PointCoverageCalculator(ITraitCollectionContext context, int credit) {
    this.context = context;
    this.credit = credit;
  }

  public int pointCoverage(IIdentificate traitType) {
    return results.get(traitType.getId());
  }

  public void calculateFor(ITraitGroup traitGroup) {
    String[] ids = traitGroup.getTraitIds();
    if (context.getExperience().isExperienced()) {
      for (String type : ids) {
        results.put(type, 0);
      }
      return;
    }
    final ITraitCollectionModel collection = context.getCollection();
    Arrays.sort(ids, new Comparator<String>() {
      @Override
      public int compare(String firstType, String secondType) {
        boolean firstFavored = collection.getTrait(firstType).getFavoredModel().getValue();
        boolean secondFavored = collection.getTrait(secondType).getFavoredModel().getValue();
        if (firstFavored && secondFavored || !(firstFavored || secondFavored)) {
          return 0;
        }
        return firstFavored ? -1 : 1;
      }
    });
    int creditLeft = credit;
    for (String id : ids) {
      int value = collection.getTrait(id).getCreationModel().getValue();
      int result = value - creditLeft;
      creditLeft = Math.max(creditLeft - value, 0);
      results.put(id, result);
    }
  }
}