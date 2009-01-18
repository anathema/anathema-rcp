package net.sf.anathema.character.experience.internal;

import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.model.AbstractModelFactory;
import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.character.core.model.template.NullModelTemplate;
import net.sf.anathema.character.experience.IExperience;

public class ExperienceFactory extends AbstractModelFactory<NullModelTemplate, IExperience> {

  private final IModelPersister<NullModelTemplate, IExperience > persister = new ExperiencePersister();

  @Override
  protected NullModelTemplate createModelTemplate(ICharacterTemplate template) {
    return new NullModelTemplate();
  }

  @Override
  protected IModelPersister<NullModelTemplate, IExperience > getPersister() {
    return persister;
  }
}