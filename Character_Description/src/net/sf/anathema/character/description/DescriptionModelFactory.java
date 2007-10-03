package net.sf.anathema.character.description;

import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.model.AbstractModelFactory;
import net.sf.anathema.character.core.model.IModelFactory;
import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.character.core.model.template.NullModelTemplate;

public class DescriptionModelFactory extends AbstractModelFactory<NullModelTemplate> implements IModelFactory {

  private final IModelPersister< NullModelTemplate , ? > persister = new CharacterDescriptionPersister();

  @Override
  protected NullModelTemplate createModelTemplate(ICharacterTemplate template) {
    return null;
  }
  
  @Override
  protected IModelPersister< NullModelTemplate , ? > getPersister() {
    return persister;
  }
}