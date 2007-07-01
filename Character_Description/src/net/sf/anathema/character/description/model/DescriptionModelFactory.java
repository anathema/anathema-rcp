package net.sf.anathema.character.description.model;

import net.sf.anathema.character.core.model.AbstractModelFactory;
import net.sf.anathema.character.core.model.IModelFactory;
import net.sf.anathema.character.core.model.IModelPersister;

public class DescriptionModelFactory extends AbstractModelFactory implements IModelFactory {

  private final IModelPersister<?> persister = new CharacterDescriptionPersister();

  @Override
  protected IModelPersister< ? > getPersister() {
    return persister;
  }
}