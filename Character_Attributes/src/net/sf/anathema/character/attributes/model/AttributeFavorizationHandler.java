package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.trait.IFavorizationHandler;

public class AttributeFavorizationHandler implements IFavorizationHandler {

  @Override
  public boolean isFavorable() {
    return false;
  }
}