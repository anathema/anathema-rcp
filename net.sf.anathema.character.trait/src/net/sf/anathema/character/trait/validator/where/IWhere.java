package net.sf.anathema.character.trait.validator.where;

import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.trait.IBasicTrait;

public interface IWhere {

  public boolean evaluate(String templateId, IModelContainer container, String modelId, IBasicTrait trait);
}