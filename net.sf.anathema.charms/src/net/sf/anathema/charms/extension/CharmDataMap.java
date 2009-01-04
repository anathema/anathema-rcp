package net.sf.anathema.charms.extension;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.data.ICharmDataMap;
import net.sf.anathema.charms.tree.ICharmId;

public class CharmDataMap extends AbstractExecutableExtension implements ICharmDataMap {

  private final Iterable<ICharmDataMap> allMaps;

  public CharmDataMap(Iterable<ICharmDataMap> allMaps) {
    this.allMaps = allMaps;
  }

  @Override
  public CharmDto getData(ICharmId charmId) {
    for (ICharmDataMap map : allMaps) {
      CharmDto data = map.getData(charmId);
      if (data != null) {
        return data;
      }
    }
    return new CharmDto();
  }
}