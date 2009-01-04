package net.sf.anathema.charms.extension;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.data.ICharmDataMap;
import net.sf.anathema.charms.tree.ICharmId;

public class CharmDataMap extends AbstractExecutableExtension implements ICharmDataMap {

  private final ICharmDataMap[] allMaps;

  public static ICharmDataMap Create() {
    return new CharmDataMap(new CharmDataExtensionPoint());
  }

  private CharmDataMap(ICharmDataMap... allMaps) {
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