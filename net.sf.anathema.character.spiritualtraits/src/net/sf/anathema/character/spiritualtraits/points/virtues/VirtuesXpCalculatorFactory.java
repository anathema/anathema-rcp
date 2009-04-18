package net.sf.anathema.character.spiritualtraits.points.virtues;

import net.sf.anathema.character.spiritualtraits.virtues.Virtues;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.points.ICalculator;
import net.sf.anathema.character.trait.points.CurrentRatingExperienceCalculator;

public class VirtuesXpCalculatorFactory {

  private final Virtues virtues;

  public VirtuesXpCalculatorFactory(final ITraitCollectionModel spiritualTraits) {
    this.virtues = new Virtues(spiritualTraits);
  }

  public ICalculator create() {
    final IBasicTrait[] traits = virtues.getTraits();
    return new CurrentRatingExperienceCalculator(3, traits);
  }
}