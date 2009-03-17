package net.sf.anathema.character.trait.validator.extension;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.trait.validator.ISpecialValidator;
import net.sf.anathema.character.trait.validator.where.ValidationDto;

public class DummySpecialValidator extends UnconfiguredExecutableExtension implements ISpecialValidator {

  private ValidationDto validationObject;

  @Override
  public void initValidation(ValidationDto validationDto) {
    this.validationObject = validationDto;
  }

  public ValidationDto getValidationObject() {
    return validationObject;
  }

  @Override
  public int getValidValue(int value) {
    return 0;
  }
}