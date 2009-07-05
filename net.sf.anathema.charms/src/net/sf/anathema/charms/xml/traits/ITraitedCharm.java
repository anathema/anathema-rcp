package net.sf.anathema.charms.xml.traits;

import net.sf.anathema.charms.traits.CharmTraits;
import net.sf.anathema.charms.tree.ICharmId;
import net.sf.anathema.lib.exception.PersistenceException;

public interface ITraitedCharm {

  public boolean hasId(ICharmId charmId);

  public CharmTraits createDto() throws PersistenceException;
}