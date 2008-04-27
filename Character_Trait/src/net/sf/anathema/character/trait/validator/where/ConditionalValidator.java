package net.sf.anathema.character.trait.validator.where;

import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.validator.IValidator;

public class ConditionalValidator implements IValidator {

  private final IValidator validator;
  private final IWhere where;
  private final String templateId;
  private final IModelContainer modelContainer;
  private final String modelId;
  private final IBasicTrait trait;

  public ConditionalValidator(
      IValidator validator,
      IWhere where,
      String templateId,
      IModelContainer modelContainer,
      String modelId,
      IBasicTrait trait) {
    this.validator = validator;
    this.where = where;
    this.templateId = templateId;
    this.modelContainer = modelContainer;
    this.modelId = modelId;
    this.trait = trait;
  }

  @Override
  public int getValidValue(int value) {
    if (where.evaluate(templateId, modelContainer, modelId, trait)) {
      return validator.getValidValue(value);
    }
    return value;
  }
}