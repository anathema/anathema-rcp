package net.sf.anathema.character.caste.persistence;

import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.model.AbstractModelFactory;
import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.character.core.model.template.IModelTemplate;
import net.sf.anathema.character.core.model.template.NullModelTemplate;

public class CasteModelFactory extends AbstractModelFactory<IModelTemplate> {
  private final CasteModelPersister persister = new CasteModelPersister();

  @Override
  protected IModelTemplate createModelTemplate(ICharacterTemplate template) {
    return new NullModelTemplate();
  }

  @Override
  protected IModelPersister<IModelTemplate, ? > getPersister() {
    return persister;
  }
}