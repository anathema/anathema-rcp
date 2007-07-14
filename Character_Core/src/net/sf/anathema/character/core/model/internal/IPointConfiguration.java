package net.sf.anathema.character.core.model.internal;

import net.sf.anathema.character.core.model.ICharacterId;

public interface IPointConfiguration {

  public String getName();

  public String getPoints(ICharacterId characterId);
}