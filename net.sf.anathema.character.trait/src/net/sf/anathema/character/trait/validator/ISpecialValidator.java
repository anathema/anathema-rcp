package net.sf.anathema.character.trait.validator;

import net.sf.anathema.character.trait.validator.where.ValidationDto;

import org.eclipse.core.runtime.IExecutableExtension;

public interface ISpecialValidator extends IValidator, IExecutableExtension {

  public void initValidation(ValidationDto validationObject);
}