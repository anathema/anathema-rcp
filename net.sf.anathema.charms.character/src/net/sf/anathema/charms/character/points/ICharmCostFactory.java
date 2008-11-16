package net.sf.anathema.charms.character.points;

import net.sf.anathema.character.core.character.ICharacterId;

public interface ICharmCostFactory {

  public ICharmCost create(ICharacterId id);
}