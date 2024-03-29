package net.sf.anathema.character.trait;

import net.sf.anathema.lib.util.IIdentificate;

public interface IFavorizationInteraction {

  public boolean isFavorable();

  public void toggleFavored(IIdentificate traitType);
}