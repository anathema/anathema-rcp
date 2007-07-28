package net.sf.anathema.character.attributes.points;

import net.sf.anathema.character.attributes.model.IAttributes;
import net.sf.anathema.character.trait.IBasicTrait;

public class AttributeExperienceCalculator {

  private final IAttributes attributes;

  public AttributeExperienceCalculator(IAttributes attributes) {
    this.attributes = attributes;
  }

  public int calculate() {
    int sum = 0;
    for (IBasicTrait attribute : attributes.getTraits()) {
      sum += calculate(attribute);
    }
    return sum;
  }

  private int calculate(IBasicTrait attribute) {
    int experienceValue = attribute.getExperiencedModel().getValue();
    int creationValue = attribute.getCreationModel().getValue();
    int increasedCurrentValueSum = 0;
    for (int experienceValueStep = creationValue; experienceValueStep < experienceValue; experienceValueStep++) {
      increasedCurrentValueSum += experienceValueStep;
    }
    return increasedCurrentValueSum * 4;
  }
}