package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.collection.TraitCollection;
import net.sf.anathema.character.trait.persistence.AbstractTraitCollectionPersister;


public class AttributesPersister extends AbstractTraitCollectionPersister<AttributeTemplate, ITraitCollectionModel> {

  @Override
  public ITraitCollectionModel createNew(AttributeTemplate attributeTemplate) {
    return TraitCollection.create(attributeTemplate.getGroups(), attributeTemplate.getTraitTemplate());
  }

  @Override
  protected ITraitCollectionModel createModelFor(IBasicTrait[] traits) {
    return new TraitCollection(traits);
  }
}