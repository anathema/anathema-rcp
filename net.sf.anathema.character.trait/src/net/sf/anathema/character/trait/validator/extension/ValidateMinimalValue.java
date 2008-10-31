package net.sf.anathema.character.trait.validator.extension;

import net.sf.anathema.character.trait.validator.IValidator;

public class ValidateMinimalValue implements IValidator {

  private final int minimalValue;

  public ValidateMinimalValue(int minimalValue) {
    this.minimalValue = minimalValue;
  }

  @Override
  public int getValidValue(int value) {
    return Math.max(minimalValue, value);
  }
}