package net.sf.anathema.character.trait.validator;

import java.util.List;

import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.model.IMinimalValueFactory;

public interface IValidatorFactory {

  public List<IValidator> create(IModelContainer container, IBasicTrait trait, IMinimalValueFactory minValueFactory);
}