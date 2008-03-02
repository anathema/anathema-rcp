package net.sf.anathema.character.trait.persistence;

import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.collection.TraitCollection;


public class TraitCollectionPersister extends AbstractTraitCollectionPersister<ITraitCollectionTemplate, ITraitCollectionModel> {

  @Override
  public ITraitCollectionModel createNew(ITraitCollectionTemplate attributeTemplate) {
    return TraitCollection.create(attributeTemplate.getGroups(), attributeTemplate.getTraitTemplate());
  }

  @Override
  protected ITraitCollectionModel createModelFor(IBasicTrait[] traits) {
    return new TraitCollection(traits);
  }
}