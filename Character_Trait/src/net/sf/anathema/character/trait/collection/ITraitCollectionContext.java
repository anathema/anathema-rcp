package net.sf.anathema.character.trait.collection;

import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.group.ITraitGroup;
import net.sf.anathema.character.trait.template.ITraitTemplate;

public interface ITraitCollectionContext {

  public IExperience getExperience();
  
  public ITraitCollectionModel getCollection();

  public ITraitGroup[] getTraitGroups();

  public ITraitTemplate getTraitTemplate();
  
  public String getActiveImageId();
}