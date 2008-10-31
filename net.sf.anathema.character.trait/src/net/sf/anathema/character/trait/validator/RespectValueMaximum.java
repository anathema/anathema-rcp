package net.sf.anathema.character.trait.validator;

import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.display.DisplayTrait;

public class RespectValueMaximum implements IValidator {

  private final IExperience experience;

  public RespectValueMaximum(IExperience experience) {
    this.experience = experience;
  }

  @Override
  public int getValidValue(int value) {
    return Math.min(value, DisplayTrait.getCurrentEssenceValue(experience));
  }
}