package net.sf.anathema.character.freebies.configuration.internal;

import net.sf.anathema.character.core.model.ICharacterId;

public final class CreditManager implements ICreditManager {
  @Override
  public int getCredit(ICharacterId characterId, String creditId) {
    return 6;
  }

  @Override
  public boolean hasCredit(String templateId, String creditId) {
    return true;
  }
}