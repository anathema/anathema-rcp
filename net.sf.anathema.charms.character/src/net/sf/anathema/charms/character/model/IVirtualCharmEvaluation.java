package net.sf.anathema.charms.character.model;

import net.sf.anathema.charms.tree.ICharmId;

public interface IVirtualCharmEvaluation {

  public boolean isVirtual(final String pattern);

  public boolean isVirtual(ICharmId charmId);

}