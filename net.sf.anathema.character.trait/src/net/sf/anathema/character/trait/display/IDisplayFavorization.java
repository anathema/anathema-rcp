package net.sf.anathema.character.trait.display;

import net.sf.anathema.character.trait.status.ITraitStatusModel;

public interface IDisplayFavorization {

  public boolean isFavorable();

  public boolean isFavored();
  
  public ITraitStatusModel getStatusModel();
}