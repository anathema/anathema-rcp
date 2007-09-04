package net.sf.anathema.character.trait.display;

import net.sf.anathema.character.trait.IFavorizationHandler;

public class DummyFavorizationHandler implements IFavorizationHandler {

  @Override
  public boolean isFavorable() {
    return false;
  }
}