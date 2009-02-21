package net.sf.anathema.character.trait.validator;

import java.util.List;

import net.sf.anathema.character.trait.validator.where.ValidationDto;

public interface IValidatorFactory {

  public List<IValidator> create(ValidationDto validationObject);
}