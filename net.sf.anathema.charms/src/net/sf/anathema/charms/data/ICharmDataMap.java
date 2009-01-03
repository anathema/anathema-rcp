package net.sf.anathema.charms.data;

import net.sf.anathema.charms.tree.ICharmId;

import org.eclipse.core.runtime.IExecutableExtension;

public interface ICharmDataMap extends IExecutableExtension {

  public CharmDto getData(ICharmId charmId);
}