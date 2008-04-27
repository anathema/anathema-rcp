package net.sf.anathema.character.trait.validator;

import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;

public class RespectCreationValueMinimum implements IValidator {

  private final IExperience experience;
  private final IBasicTrait basicTrait;

  public RespectCreationValueMinimum(IExperience experience, IBasicTrait basicTrait) {
    this.experience = experience;
    this.basicTrait = basicTrait;
  }

  @Override
  public int getValidValue(int value) {
    if (experience.isExperienced() && value < basicTrait.getCreationModel().getValue()) {
      return basicTrait.getCreationModel().getValue();
    }
    return value;
  }
}