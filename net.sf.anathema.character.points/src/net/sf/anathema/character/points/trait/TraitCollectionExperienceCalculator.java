package net.sf.anathema.character.points.trait;

import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class TraitCollectionExperienceCalculator {

  private final ITraitCollectionModel collection;
  private final int base;
  private final Integer newCost;

  public TraitCollectionExperienceCalculator(ITraitCollectionModel collection, int base, Integer newCost) {
    this.collection = collection;
    this.base = base;
    this.newCost = newCost;
  }

  public int calculate() {
    int sum = 0;
    for (IBasicTrait attribute : collection.getAllTraits()) {
      sum += calculate(attribute);
    }
    return sum;
  }

  private int calculate(IBasicTrait attribute) {
    int experienceValue = attribute.getExperiencedModel().getValue();
    int creationValue = attribute.getCreationModel().getValue();
    int increasedCurrentValueSum = 0;
    int favoredReduction = 0;
    for (int experienceValueStep = Math.max(1, creationValue); experienceValueStep < experienceValue; experienceValueStep++) {
      increasedCurrentValueSum += experienceValueStep;
      if (attribute.getStatusManager().getStatus().isCheap()) {
        favoredReduction++;
      }
    }
    int newLearningCost = creationValue == 0 && experienceValue > 0 ? newCost : 0;
    return increasedCurrentValueSum * base - favoredReduction + newLearningCost;
  }
}