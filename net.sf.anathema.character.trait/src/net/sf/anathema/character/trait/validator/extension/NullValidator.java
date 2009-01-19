package net.sf.anathema.character.trait.validator.extension;

import net.sf.anathema.character.trait.validator.IValidator;

public class NullValidator implements IValidator {

  @Override
  public int getValidValue(int value) {
    return value;
  }
}