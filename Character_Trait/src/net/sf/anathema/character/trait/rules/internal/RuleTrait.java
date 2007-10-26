package net.sf.anathema.character.trait.rules.internal;

import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.rules.ITraitTemplate;

public class RuleTrait implements IRuleTrait {

  private final IBasicTrait basicTrait;
  private final IExperience experience;
  private final ITraitTemplate traitTemplate;

  public RuleTrait(IBasicTrait basicTrait, IExperience experience, ITraitTemplate traitTemplate) {
    this.basicTrait = basicTrait;
    this.experience = experience;
    this.traitTemplate = traitTemplate;
  }

  @Override
  public int getMaximalValue() {
    return experience.isExperienced() ? 7 : traitTemplate.getMaximalValue();
  }

  @Override
  public void setValue(int value) {
    int correctedValue = getCorrectedValue(value);
    if (experience.isExperienced()) {
      basicTrait.getExperiencedModel().setValue(correctedValue);
    }
    else {
      basicTrait.getCreationModel().setValue(correctedValue);
    }
  }

  private int getCorrectedValue(int value) {
    if (experience.isExperienced() && value < basicTrait.getCreationModel().getValue()) {
      return basicTrait.getCreationModel().getValue();
    }
    if (value < traitTemplate.getMinimalValue()) {
      return traitTemplate.getMinimalValue();
    }
    if (value > traitTemplate.getMaximalValue()) {
      return traitTemplate.getMaximalValue();
    }
    return value;
  }

  @Override
  public int getValue() {
    int experiencedValue = basicTrait.getExperiencedModel().getValue();
    int creationValue = basicTrait.getCreationModel().getValue();
    boolean isExperienced = experience.isExperienced();
    if (isExperienced && experiencedValue >= creationValue) {
      return experiencedValue;
    }
    return creationValue;
  }
}