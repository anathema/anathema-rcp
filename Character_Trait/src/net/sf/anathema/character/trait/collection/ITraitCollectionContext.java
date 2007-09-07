package net.sf.anathema.character.trait.collection;

import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IFavorizationHandler;
import net.sf.anathema.character.trait.group.ITraitGroup;
import net.sf.anathema.character.trait.rules.ITraitTemplate;

public interface ITraitCollectionContext {

  public IExperience getExperience();
  
  public ITraitCollectionModel getCollection();
  
  public IFavorizationHandler getFavorizationHandler();

  public ITraitGroup[] getTraitGroups();

  public ITraitTemplate getTraitTemplate();
}