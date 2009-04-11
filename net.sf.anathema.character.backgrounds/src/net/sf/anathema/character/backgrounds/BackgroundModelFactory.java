package net.sf.anathema.character.backgrounds;

import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.model.AbstractModelFactory;
import net.sf.anathema.character.core.model.IModelPersister;

public class BackgroundModelFactory extends AbstractModelFactory<BackgroundTemplate, IBackgroundModel> {
  private final BackgroundPersister persister = new BackgroundPersister();

  @Override
  protected BackgroundTemplate createModelTemplate(ICharacterTemplate template) {
    return new BackgroundTemplate();
  }

  @Override
  protected IModelPersister<BackgroundTemplate, IBackgroundModel> getPersister() {
    return persister;
  }
}