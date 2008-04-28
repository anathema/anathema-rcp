package net.sf.anathema.character.trait.validator.where;

import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.trait.IBasicTrait;

public class WhereTemplateId implements IWhere {

  private final String expectedTemplateId;

  public WhereTemplateId(String expectedTemplateId) {
    this.expectedTemplateId = expectedTemplateId;
  }

  @Override
  public boolean evaluate(String templateId, IModelContainer container, String modelId, IBasicTrait trait) {
    return expectedTemplateId.equals(templateId);
  }
}
