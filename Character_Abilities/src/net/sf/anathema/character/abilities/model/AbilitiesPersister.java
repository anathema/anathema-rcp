package net.sf.anathema.character.abilities.model;

import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.collection.TraitCollection;
import net.sf.anathema.character.trait.persistence.AbstractTraitCollectionPersister;


public class AbilitiesPersister extends AbstractTraitCollectionPersister<AbilitiesTemplate, ITraitCollectionModel> {

  @Override
  public ITraitCollectionModel createNew(AbilitiesTemplate attributeTemplate) {
    return TraitCollection.create(attributeTemplate.getGroups(), attributeTemplate.getTraitTemplate());
  }

  @Override
  protected ITraitCollectionModel createModelFor(IBasicTrait[] traits) {
    return new TraitCollection(traits);
  }
}