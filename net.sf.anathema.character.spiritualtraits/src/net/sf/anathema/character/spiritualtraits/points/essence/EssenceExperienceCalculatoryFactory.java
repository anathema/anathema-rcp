package net.sf.anathema.character.spiritualtraits.points.essence;

import static net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants.*;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.points.CurrentRatingExperienceCalculator;
import net.sf.anathema.lib.lang.ICalculator;

public class EssenceExperienceCalculatoryFactory {

  private final ITraitCollectionModel spiritualTraits;
  private final int experienceCost;

  public EssenceExperienceCalculatoryFactory(final ITraitCollectionModel spiritualTraits, final int experienceCost) {
    this.spiritualTraits = spiritualTraits;
    this.experienceCost = experienceCost;
  }

  public ICalculator create() {
    final IBasicTrait essence = spiritualTraits.getTrait(ESSENCE_ID);
    return new CurrentRatingExperienceCalculator(experienceCost, essence);
  }
}