package net.sf.anathema.character.points.problems;

import net.sf.anathema.character.core.character.ICharacterId;

public interface IBonusPointProvider {

  public int getAvailableBonusPoints(ICharacterId characterId);
}