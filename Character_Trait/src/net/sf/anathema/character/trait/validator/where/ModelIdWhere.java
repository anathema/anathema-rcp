package net.sf.anathema.character.trait.validator.where;

import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.trait.IBasicTrait;

public class ModelIdWhere implements IWhere {

  private final String expectedModelId;


  public ModelIdWhere(String modelId) {
    expectedModelId = modelId;
  }

  @Override
  public boolean evaluate(String templateId, IModelContainer container, String modelId, IBasicTrait trait) {
    return expectedModelId.equals(modelId);
  }
}