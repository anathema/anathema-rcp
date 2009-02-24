package net.sf.anathema.character.spiritualtraits.points.essence;

import static net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants.*;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.points.AbstractPointWiseExperienceCalculator;

public class EssenceExperienceCalculator extends AbstractPointWiseExperienceCalculator {

  private final ITraitCollectionModel spiritualTraits;

  public EssenceExperienceCalculator(ITraitCollectionModel spiritualTraits, int experienceCost) {
    super(experienceCost);
    this.spiritualTraits = spiritualTraits;
  }

  @Override
  protected IBasicTrait[] getCalculationTraits() {
    IBasicTrait essence = spiritualTraits.getTrait(ESSENCE_TRAIT_ID);
    return new IBasicTrait[] { essence };
  }
}
