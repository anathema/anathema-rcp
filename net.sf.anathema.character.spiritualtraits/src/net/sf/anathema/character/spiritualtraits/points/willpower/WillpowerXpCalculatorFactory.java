package net.sf.anathema.character.spiritualtraits.points.willpower;

import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.points.ICalculator;
import net.sf.anathema.character.trait.points.CurrentRatingExperienceCalculator;

public class WillpowerXpCalculatorFactory {


  private final IBasicTrait trait;

  public WillpowerXpCalculatorFactory(ITraitCollectionModel model) {
    trait = model.getTrait(IPluginConstants.WILLPOWER_ID);
  }

  public ICalculator create() {
    return new CurrentRatingExperienceCalculator(2, trait);
  }
}