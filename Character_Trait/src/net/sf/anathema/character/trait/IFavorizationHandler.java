package net.sf.anathema.character.trait;

import net.sf.anathema.lib.util.IIdentificate;

public interface IFavorizationHandler {

  public boolean isFavorable();

  public void toogleFavored(IIdentificate traitType);
}