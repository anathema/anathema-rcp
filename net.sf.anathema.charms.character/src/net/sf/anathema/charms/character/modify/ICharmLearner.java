package net.sf.anathema.charms.character.modify;

import net.sf.anathema.charms.tree.ICharmId;

public interface ICharmLearner {

  public boolean knows(ICharmId charmId);

  public void learn(ICharmId charmId);
}