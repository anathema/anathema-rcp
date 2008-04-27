package net.sf.anathema.character.trait.validator.where;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.validator.IValidator;
import net.sf.anathema.character.trait.validator.IValidatorFactory;

public class ConditionalFactory implements IValidatorFactory {

  private final IValidator validator;
  private final IWhere whereClause;

  public ConditionalFactory(IValidator validator, IWhere whereClause) {
    this.validator = validator;
    this.whereClause = whereClause;
  }

  @Override
  public List<IValidator> create(String templateId, IModelContainer container, String modelId, IBasicTrait trait) {
    List<IValidator> validators = new ArrayList<IValidator>();
    if (whereClause.evaluate(templateId, container, modelId, trait)) {
      validators.add(validator);
    }
    return validators;
  }
}