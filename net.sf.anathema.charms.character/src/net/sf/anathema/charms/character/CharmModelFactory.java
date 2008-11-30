package net.sf.anathema.charms.character;

import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.model.AbstractModelFactory;
import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.character.core.model.template.NullModelTemplate;
import net.sf.anathema.charms.character.model.ICharmModel;

public class CharmModelFactory extends AbstractModelFactory<NullModelTemplate, ICharmModel> {

  private final CharmsPersister charmsPersister = new CharmsPersister();

  @Override
  protected NullModelTemplate createModelTemplate(ICharacterTemplate template) {
    return null;
  }

  @Override
  protected IModelPersister<NullModelTemplate, ICharmModel> getPersister() {
    return charmsPersister;
  }
}