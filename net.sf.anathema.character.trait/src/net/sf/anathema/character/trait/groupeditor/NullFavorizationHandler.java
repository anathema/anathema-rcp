package net.sf.anathema.character.trait.groupeditor;

import net.sf.anathema.character.trait.IFavorizationHandler;
import net.sf.anathema.lib.util.IIdentificate;

public class NullFavorizationHandler implements IFavorizationHandler {

  @Override
  public boolean isFavorable() {
    return false;
  }

  @Override
  public void toggleFavored(IIdentificate traitType) {
    // nothing to do
  }
}