package net.sf.anathema.character.points.configuration.internal;

import net.sf.anathema.character.core.character.ICharacterId;


public interface IPointConfiguration {

  public String getName();

  public String getPoints(ICharacterId characterId);
}