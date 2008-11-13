package net.sf.anathema.character.trait.fake;

import net.sf.anathema.character.trait.IFavorizationInteraction;
import net.sf.anathema.lib.util.IIdentificate;

public class DummyFavorizationHandler implements IFavorizationInteraction {

  @Override
  public boolean isFavorable() {
    return false;
  }

  @Override
  public void toggleFavored(IIdentificate traitType) {
    // nothing to do
  }
}