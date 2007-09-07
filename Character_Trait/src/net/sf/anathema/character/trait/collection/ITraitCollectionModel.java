package net.sf.anathema.character.trait.collection;

import net.sf.anathema.character.core.model.IModel;
import net.sf.anathema.character.trait.IBasicTrait;

public interface ITraitCollectionModel extends IModel {
  
  public IBasicTrait[] getTraits();

  public IBasicTrait getTrait(String id);
}