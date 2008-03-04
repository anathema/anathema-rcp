package net.sf.anathema.character.points.trait;

import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class TraitCollectionExperienceCalculator {

  private final ITraitCollectionModel collection;
  private final int base;

  public TraitCollectionExperienceCalculator(ITraitCollectionModel collection, int base) {
    this.collection = collection;
    this.base = base;
  }

  public int calculate() {
    int sum = 0;
    for (IBasicTrait attribute : collection.getTraits()) {
      sum += calculate(attribute);
    }
    return sum;
  }

  private int calculate(IBasicTrait attribute) {
    int experienceValue = attribute.getExperiencedModel().getValue();
    int creationValue = attribute.getCreationModel().getValue();
    int increasedCurrentValueSum = 0;
    int favoredReduction = 0;
    for (int experienceValueStep = creationValue; experienceValueStep < experienceValue; experienceValueStep++) {
      increasedCurrentValueSum += experienceValueStep;
      if (attribute.getStatusManager().getStatus().isCheap()) {
        favoredReduction++;
      }
    }
    return increasedCurrentValueSum * base - favoredReduction;
  }
}