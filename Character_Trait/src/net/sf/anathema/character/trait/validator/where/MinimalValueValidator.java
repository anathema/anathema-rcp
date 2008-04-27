package net.sf.anathema.character.trait.validator.where;

import net.sf.anathema.character.trait.validator.IValidator;

public class MinimalValueValidator implements IValidator {

  private final int minimalValue;

  public MinimalValueValidator(int minimalValue) {
    this.minimalValue = minimalValue;
  }

  @Override
  public int getValidValue(int value) {
    return Math.max(minimalValue, value);
  }
}