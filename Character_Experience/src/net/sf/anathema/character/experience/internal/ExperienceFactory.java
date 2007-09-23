package net.sf.anathema.character.experience.internal;

import net.sf.anathema.character.core.model.AbstractModelFactory;
import net.sf.anathema.character.core.model.IModelFactory;
import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.character.core.model.template.NullModelTemplate;

public class ExperienceFactory extends AbstractModelFactory<NullModelTemplate>  implements IModelFactory {

  private final IModelPersister<NullModelTemplate, ?> persister = new ExperiencePersister();

  @Override
  protected NullModelTemplate createModelTemplate() {
    return null;
  }
  
  @Override
  protected IModelPersister< NullModelTemplate , ? > getPersister() {
    return persister;
  }
}