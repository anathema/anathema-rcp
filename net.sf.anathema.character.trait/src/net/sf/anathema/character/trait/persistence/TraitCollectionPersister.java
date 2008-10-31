package net.sf.anathema.character.trait.persistence;

import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.collection.TraitCollection;
import net.sf.anathema.character.trait.collection.TraitCollectionModelFactory;
import net.sf.anathema.character.trait.model.ITraitCollectionTemplate;


public class TraitCollectionPersister extends AbstractTraitCollectionPersister<ITraitCollectionTemplate, ITraitCollectionModel> {

  @Override
  public ITraitCollectionModel createNew(ITraitCollectionTemplate template) {
    return TraitCollectionModelFactory.create(template.getGroupTemplate().getGroups());
  }

  @Override
  protected ITraitCollectionModel createModelFor(IBasicTrait[] traits) {
    return new TraitCollection(traits);
  }
}