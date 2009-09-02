package net.sf.anathema.charms.data;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.charms.tree.ICharmId;

public class CachingCharmDataMap implements ICharmDataMap {
  
  private final ICharmDataMap charmDataMap;
  private Map<ICharmId, CharmDto> cache = new HashMap<ICharmId, CharmDto>();

  public CachingCharmDataMap(ICharmDataMap charmDataMap) {
    this.charmDataMap = charmDataMap;
  }

  @Override
  public CharmDto getData(ICharmId charmId) {
    if (!cache.containsKey(charmId)) {
      CharmDto data = charmDataMap.getData(charmId);
      cache.put(charmId, data);
    }
    return cache.get(charmId);
  }
}