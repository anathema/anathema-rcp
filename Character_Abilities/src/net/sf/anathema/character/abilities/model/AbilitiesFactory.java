package net.sf.anathema.character.abilities.model;

import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.model.AbstractModelFactory;
import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.persistence.ITraitCollectionTemplate;
import net.sf.anathema.character.trait.persistence.TraitCollectionPersister;

public class AbilitiesFactory extends AbstractModelFactory<ITraitCollectionTemplate, ITraitCollectionModel> {

  private final IModelPersister<ITraitCollectionTemplate, ITraitCollectionModel> persister = new TraitCollectionPersister();

  @Override
  protected ITraitCollectionTemplate createModelTemplate(ICharacterTemplate template) {
    return new AbilitiesTemplateProvider().getAttributeTemplate(template.getId());
  }

  @Override
  protected IModelPersister<ITraitCollectionTemplate, ITraitCollectionModel> getPersister() {
    return persister;
  }
}