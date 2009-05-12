package net.sf.anathema.character.experience.points;

import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.model.AbstractModelFactory;
import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.character.core.model.template.NullModelTemplate;
import net.sf.anathema.character.experience.IExperiencePoints;

public class ExperiencePointsFactory extends AbstractModelFactory<NullModelTemplate, IExperiencePoints> {

  private final IModelPersister<NullModelTemplate, IExperiencePoints> persister = new ExperiencePointsPersister();

  @Override
  protected NullModelTemplate createModelTemplate(ICharacterTemplate template) {
    return new NullModelTemplate();
  }

  @Override
  protected IModelPersister<NullModelTemplate, IExperiencePoints> getPersister() {
    return persister;
  }
}