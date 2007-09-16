package net.sf.anathema.character.attributes.points;

import java.util.Arrays;
import java.util.Comparator;

import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.ITraitGroup;

public class PointCoverageCalculator {

  private final ITraitCollectionContext context;
  private final int credit;

  public PointCoverageCalculator(ITraitCollectionContext context, int credit) {
    this.context = context;
    this.credit = credit;
  }

  public ICoverageCalculation calculateCoverageFor(ITraitGroup traitGroup) {
    String[] ids = traitGroup.getTraitIds();
    if (context.getExperience().isExperienced()) {
      return new NullCoverageCalculation();
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
    // int maximalValue = context.getTraitTemplate().getMaximalValue();
    CoverageCalculation calculation = new CoverageCalculation();
    int creditLeft = credit;
    for (String id : ids) {
      int value = collection.getTrait(id).getCreationModel().getValue();
      int result = ((value - IAttributeConstants.ATTRIBUTE_CALCULATION_BASE) - creditLeft);
      creditLeft = Math.max(creditLeft - (value - IAttributeConstants.ATTRIBUTE_CALCULATION_BASE), 0);
      calculation.addResult(id, result);
    }
    return calculation;
  }
}