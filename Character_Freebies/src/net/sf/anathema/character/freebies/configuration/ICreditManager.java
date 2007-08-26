package net.sf.anathema.character.freebies.configuration;

import net.sf.anathema.character.core.model.ICharacterId;

public interface ICreditManager {

  public int getCredit(ICharacterId characterId, String creditId);

  public boolean hasCredit(String templateId, String creditId);
}