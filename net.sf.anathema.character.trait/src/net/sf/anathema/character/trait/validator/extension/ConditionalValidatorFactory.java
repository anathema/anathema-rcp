package net.sf.anathema.character.trait.validator.extension;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.character.trait.validator.IValidator;
import net.sf.anathema.character.trait.validator.IValidatorFactory;
import net.sf.anathema.character.trait.validator.where.IWhere;
import net.sf.anathema.character.trait.validator.where.ValidationDto;

public class ConditionalValidatorFactory implements IValidatorFactory {

  private final IWhere whereClause;
  private final IExtensionElement validatorElement;

  public ConditionalValidatorFactory(IExtensionElement validatorElement, IWhere whereClause) {
    this.validatorElement = validatorElement;
    this.whereClause = whereClause;
  }

  @Override
  public List<IValidator> create(ValidationDto dto) {
    List<IValidator> unconditionedValidators = new UnconditionalValidatorFactory(validatorElement, dto).create();
    return transformToConditionalValidators(unconditionedValidators, dto);
  }

  private List<IValidator> transformToConditionalValidators(
      List<IValidator> unconditionedValidators,
      ValidationDto validationObject) {
    List<IValidator> conditionedValidators = new ArrayList<IValidator>();
    for (IValidator validator : unconditionedValidators) {
      conditionedValidators.add(new ConditionalValidator(validator, whereClause, validationObject));
    }
    return conditionedValidators;
  }
}