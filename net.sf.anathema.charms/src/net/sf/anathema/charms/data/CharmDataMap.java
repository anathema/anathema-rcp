package net.sf.anathema.charms.data;

import net.sf.anathema.charms.tree.ICharmId;

public class CharmDataMap implements ICharmDataMap {

  private final Iterable< ? extends ICharmDataMap> allMaps;

  public CharmDataMap(Iterable< ? extends ICharmDataMap> allMaps) {
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