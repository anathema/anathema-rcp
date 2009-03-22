package net.sf.anathema.character.spiritualtraits.virtues;

import java.util.Arrays;

import net.sf.anathema.character.spiritualtraits.validator.ReverseCreationValueComparator;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class VirtueSumCalculator {

  private final IBasicTrait[] virtues;

  public VirtueSumCalculator(ITraitCollectionModel spiritualModel) {
    IBasicTrait[] virtueTraits = new Virtues(spiritualModel).getTraits();
    this.virtues = Arrays.copyOf(virtueTraits, virtueTraits.length);
  }

  public int getSumOfTwoHighestVirtues() {
    Arrays.sort(virtues, 0, virtues.length, new ReverseCreationValueComparator());
    int virtueSum = virtues[0].getCreationModel().getValue() + virtues[1].getCreationModel().getValue();
    return virtueSum;
  }
}
