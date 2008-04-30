package net.sf.anathema.character.trait.collection;

import net.sf.anathema.character.trait.model.IFavorizationTemplate;

public class FavorizationTemplate implements IFavorizationTemplate {

  private final int favoredCount;

  public FavorizationTemplate(int favoredCount) {
    this.favoredCount = favoredCount;
  }
  
  @Override
  public int getFavorizationCount() {
    return favoredCount;
  }
}