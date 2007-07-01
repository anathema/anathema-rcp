package net.sf.anathema.character.experience;

import net.sf.anathema.character.core.model.AbstractModelFactory;
import net.sf.anathema.character.core.model.IModelFactory;
import net.sf.anathema.character.core.model.IModelPersister;

public class ExperienceModelFactory extends AbstractModelFactory  implements IModelFactory {

  private final IModelPersister<?> persister = new ExperienceModelPersister();
  
  @Override
  protected IModelPersister< ? > getPersister() {
    return persister;
  }
}