package net.sf.anathema.character.trait.persistence;

import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.collection.TraitCollection;
import net.sf.anathema.character.trait.collection.TraitCollectionFactory;


public class TraitCollectionPersister extends AbstractTraitCollectionPersister<ITraitCollectionTemplate, ITraitCollectionModel> {

  @Override
  public ITraitCollectionModel createNew(ITraitCollectionTemplate template) {
    return TraitCollectionFactory.create(template.getGroups(), template);
  }

  @Override
  protected ITraitCollectionModel createModelFor(IBasicTrait[] traits) {
    return new TraitCollection(traits);
  }
}