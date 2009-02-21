package net.sf.anathema.character.trait.validator.extension;

import net.sf.anathema.character.trait.validator.IValidator;
import net.sf.anathema.character.trait.validator.where.IWhere;
import net.sf.anathema.character.trait.validator.where.ValidationDto;

public class ConditionalValidator implements IValidator {

  private final IValidator validator;
  private final IWhere where;
  private final ValidationDto validationObject;

  public ConditionalValidator(IValidator validator, IWhere where, ValidationDto validationObject) {
    this.validator = validator;
    this.where = where;
    this.validationObject = validationObject;
  }

  @Override
  public int getValidValue(int value) {
    if (where.evaluate(validationObject)) {
      return validator.getValidValue(value);
    }
    return value;
  }
}