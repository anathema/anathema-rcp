package net.sf.anathema.character.trait.rules;

import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;

public class RuleTrait implements IRuleTrait {

  private final IBasicTrait basicTrait;
  private final IExperience experience;
  private final ITraitRules traitRules;

  public RuleTrait(IBasicTrait basicTrait, IExperience experience, ITraitRules traitRules) {
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
    int correctedValue = value;
    if (value < traitRules.getMinimalValue()) {
      correctedValue = traitRules.getMinimalValue();
    }
    if (experience.isExperienced()) {
      basicTrait.getExperiencedModel().setValue(correctedValue);
    }
    else {
      basicTrait.getCreationModel().setValue(correctedValue);
    }
  }

  @Override
  public int getValue() {
    if (experience.isExperienced() && basicTrait.getExperiencedModel().getValue() > -1) {
      return basicTrait.getExperiencedModel().getValue();
    }
    return basicTrait.getCreationModel().getValue();
  }
}