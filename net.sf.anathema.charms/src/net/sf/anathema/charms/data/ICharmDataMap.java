package net.sf.anathema.charms.data;

import net.sf.anathema.charms.tree.ICharmId;

public interface ICharmDataMap {

  /** Retrieves data for the given charm. Implementors should take care to handle generic charms correctly. */
  public CharmDto getData(ICharmId charmId);
}