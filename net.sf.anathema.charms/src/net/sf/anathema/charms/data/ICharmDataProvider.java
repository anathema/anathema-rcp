package net.sf.anathema.charms.data;

import net.sf.anathema.charms.tree.ICharmId;

public interface ICharmDataProvider {

  public String getDisplayName(ICharmId charmId);
}