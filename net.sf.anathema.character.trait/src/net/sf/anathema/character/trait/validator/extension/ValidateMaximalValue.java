package net.sf.anathema.character.trait.validator.extension;

import net.sf.anathema.character.trait.validator.IValidator;

public class ValidateMaximalValue implements IValidator {

  private final int maximalValue;

  public ValidateMaximalValue(int maximalValue) {
    this.maximalValue = maximalValue;
  }

  @Override
  public int getValidValue(int value) {
    return Math.min(maximalValue, value);
  }
}