package net.sf.anathema.character.trait.validator.where;

import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.trait.IBasicTrait;

public class ValidationDto {

  public String templateId;
  public IModelContainer container;
  public String modelId;
  public IBasicTrait trait;
  public String charactertype;

  public IModel getModel() {
    return container.getModel(modelId);
  }
}