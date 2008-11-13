package net.sf.anathema.character.trait.model;

import net.sf.anathema.lib.util.IIdentificate;

public interface IFavorizationTemplate {

  public int getAllowedFavored();

  public boolean isRequiredFavored(IIdentificate traitType);
}
