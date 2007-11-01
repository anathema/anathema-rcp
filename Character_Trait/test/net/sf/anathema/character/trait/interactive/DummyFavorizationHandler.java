package net.sf.anathema.character.trait.interactive;

import net.sf.anathema.character.trait.IFavorizationHandler;
import net.sf.anathema.lib.util.IIdentificate;

public class DummyFavorizationHandler implements IFavorizationHandler {

  @Override
  public boolean isFavorable() {
    return false;
  }

  @Override
  public void toogleFavored(IIdentificate traitType) {
    // nothing to do
  }
}