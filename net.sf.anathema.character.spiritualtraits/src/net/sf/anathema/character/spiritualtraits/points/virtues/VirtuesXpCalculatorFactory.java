package net.sf.anathema.character.spiritualtraits.points.virtues;

import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.points.ICalculator;
import net.sf.anathema.character.trait.points.PointWiseExperienceCalculator;

public class VirtuesXpCalculatorFactory {

  private final Virtues virtues;

  public VirtuesXpCalculatorFactory(final ITraitCollectionModel spiritualTraits) {
    this.virtues = new Virtues(spiritualTraits);
  }

  public ICalculator create() {
    final IBasicTrait[] traits = virtues.getTraits();
    return new PointWiseExperienceCalculator(3, traits);
  }
}