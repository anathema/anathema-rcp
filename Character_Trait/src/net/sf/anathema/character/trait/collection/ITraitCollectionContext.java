package net.sf.anathema.character.trait.collection;

import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.trait.group.ITraitGroup;
import net.sf.anathema.character.trait.template.ITraitTemplate;

public interface ITraitCollectionContext {

  public IModelContainer getModelContainer();

  public ITraitCollectionModel getCollection();

  public ITraitGroup[] getTraitGroups();

  public ITraitTemplate getTraitTemplate(String traitId);

  public String getActiveImageId();
}