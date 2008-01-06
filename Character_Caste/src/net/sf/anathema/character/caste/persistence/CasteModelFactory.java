package net.sf.anathema.character.caste.persistence;

import net.sf.anathema.character.caste.model.CasteTemplate;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.model.AbstractModelFactory;
import net.sf.anathema.character.core.model.IModelPersister;

public class CasteModelFactory extends AbstractModelFactory<CasteTemplate> {
  private final CasteModelPersister persister = new CasteModelPersister();

  @Override
  protected CasteTemplate createModelTemplate(ICharacterTemplate template) {
    return new CasteTemplate();
  }

  @Override
  protected IModelPersister<CasteTemplate, ? > getPersister() {
    return persister;
  }
}