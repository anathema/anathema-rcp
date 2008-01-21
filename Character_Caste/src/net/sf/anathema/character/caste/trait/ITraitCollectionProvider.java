package net.sf.anathema.character.caste.trait;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public interface ITraitCollectionProvider {

  public ITraitCollectionModel getModel(ICharacterId id);
}