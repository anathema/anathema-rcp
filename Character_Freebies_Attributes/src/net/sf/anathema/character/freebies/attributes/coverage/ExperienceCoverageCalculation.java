package net.sf.anathema.character.freebies.attributes.coverage;

import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.lib.util.IIdentificate;

public class ExperienceCoverageCalculation implements ICoverageCalculation {

  private final ITraitCollectionModel collection;

  public ExperienceCoverageCalculation(ITraitCollectionModel collection) {
    this.collection = collection;
  }

  @Override
  public int getPointsCovered(IIdentificate identificate) {
    return getTrait(identificate).getCreationModel().getValue();
  }

  private IBasicTrait getTrait(IIdentificate identificate) {
    return collection.getTrait(identificate.getId());
  }

  @Override
  public int getPointsNotCovered(IIdentificate identificate) {
    return getTrait(identificate).getExperiencedModel().getValue() - getPointsCovered(identificate);
  }
}