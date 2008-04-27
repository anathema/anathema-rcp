package net.sf.anathema.character.trait.collection;

import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.trait.group.ITraitGroup;

public interface ITraitCollectionContext {

  public IModelContainer getModelContainer();

  public ITraitCollectionModel getCollection();

  public ITraitGroup[] getTraitGroups();

  public int getMinimumValue(String traitId);

  public String getActiveImageId();
}