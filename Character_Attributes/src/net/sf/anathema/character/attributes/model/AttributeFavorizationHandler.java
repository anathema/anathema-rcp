package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.trait.IFavorizationHandler;

public class AttributeFavorizationHandler implements IFavorizationHandler {

  private final ICharacterId characterId;
  private final ICreditManager creditManager;

  public AttributeFavorizationHandler(ICharacterId characterId, ICreditManager creditManager) {
    this.characterId = characterId;
    this.creditManager = creditManager;
  }

  @Override
  public boolean isFavorable() {
    int credit = creditManager.getCredit(characterId, "net.sf.anathema.character.attributes.favored"); //$NON-NLS-1$
    return credit > 0;
  }
}