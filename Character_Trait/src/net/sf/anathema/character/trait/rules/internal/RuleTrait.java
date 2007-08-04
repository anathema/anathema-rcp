package net.sf.anathema.character.trait.rules.internal;

import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.rules.ITraitTemplate;

public class RuleTrait implements IRuleTrait {

  private final IBasicTrait basicTrait;
  private final IExperience experience;
  private final ITraitTemplate traitRules;

  public RuleTrait(IBasicTrait basicTrait, IExperience experience, ITraitTemplate traitRules) {
    this.basicTrait = basicTrait;
    this.experience = experience;
    this.traitRules = traitRules;
  }

  @Override
  public int getMaximalValue() {
    return traitRules.getMaximalValue();
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
    if (value < traitRules.getMinimalValue()) {
      return traitRules.getMinimalValue();
    }
    if (value > traitRules.getMaximalValue()) {
      return traitRules.getMaximalValue();
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