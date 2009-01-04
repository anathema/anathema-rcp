package net.sf.anathema.charms.character.points;

import net.sf.anathema.charms.tree.ICharmId;

public interface ICharmCost {

  public int getExperienceCost(ICharmId charmId);

  public int getBonusPointCost(ICharmId charmId);
}