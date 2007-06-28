package net.sf.anathema.character.basics;

import net.sf.anathema.character.core.model.AbstractModelFactory;
import net.sf.anathema.character.core.model.IModelFactory;
import net.sf.anathema.character.core.model.IModelPersister;

public class CharacterBasicsFactory extends AbstractModelFactory  implements IModelFactory {

  private final IModelPersister<?> persister = new CharacterBasicsPersister();
  
  @Override
  protected IModelPersister< ? > getPersister() {
    return persister;
  }
}