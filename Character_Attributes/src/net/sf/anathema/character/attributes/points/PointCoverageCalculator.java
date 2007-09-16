package net.sf.anathema.character.attributes.points;

import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.lib.util.IIdentificate;

public class PointCoverageCalculator {

  private final ITraitCollectionContext context;
  private final int credit;

  public PointCoverageCalculator(ITraitCollectionContext context, int credit) {
    this.context = context;
    this.credit = credit;
  }

  public int calculate(IIdentificate traitType) {
    if (context.getExperience().isExperienced()) {
      return 0;
    }
    return context.getCollection().getTrait(traitType.getId()).getCreationModel().getValue() - credit;
  }
}