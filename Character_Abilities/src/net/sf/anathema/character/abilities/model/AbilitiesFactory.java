package net.sf.anathema.character.abilities.model;

import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.model.AbstractModelFactory;
import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class AbilitiesFactory extends AbstractModelFactory<AbilitiesTemplate, ITraitCollectionModel> {

  private final IModelPersister<AbilitiesTemplate, ITraitCollectionModel> persister = new AbilitiesPersister();

  @Override
  protected AbilitiesTemplate createModelTemplate(ICharacterTemplate template) {
    return new AbilitiesTemplateProvider().getAttributeTemplate(template.getId());
  }

  @Override
  protected IModelPersister<AbilitiesTemplate, ITraitCollectionModel> getPersister() {
    return persister;
  }
}