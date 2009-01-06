package net.sf.anathema.charms.xml.data;

import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.tree.ICharmId;
import net.sf.anathema.lib.exception.PersistenceException;

public interface IDatedCharm {

  public boolean hasId(ICharmId charmId);

  public CharmDto createDto() throws PersistenceException;
}