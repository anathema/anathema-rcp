package net.sf.anathema.character.trait.validator;

import java.util.List;

import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.trait.IBasicTrait;

public interface IValidatorContainer {

  public List<IValidator> create(String templateId, IModelContainer container, IBasicTrait trait);
}