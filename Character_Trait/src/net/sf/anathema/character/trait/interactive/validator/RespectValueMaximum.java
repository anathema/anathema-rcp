package net.sf.anathema.character.trait.interactive.validator;

import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.display.DisplayTrait;
import net.sf.anathema.character.trait.template.ITraitTemplate;

public class RespectValueMaximum implements IValidator {

  private final IExperience experience;
  private final ITraitTemplate traitTemplate;

  public RespectValueMaximum(IExperience experience, ITraitTemplate traitTemplate) {
    this.experience = experience;
    this.traitTemplate = traitTemplate;
  }

  @Override
  public int getValidValue(int value) {
    return Math.min(value, DisplayTrait.getMaximumValue(experience, traitTemplate));
  }
}