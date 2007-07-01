package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.core.model.AbstractModelFactory;
import net.sf.anathema.character.core.model.IModelFactory;
import net.sf.anathema.character.core.model.IModelPersister;

public class AttributesFactory extends AbstractModelFactory implements IModelFactory {

  private final IModelPersister<?> persister = new AttributesPersister();

  @Override
  protected IModelPersister<?> getPersister() {
    return persister;
  }
}