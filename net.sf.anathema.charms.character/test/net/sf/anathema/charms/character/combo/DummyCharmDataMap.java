package net.sf.anathema.charms.character.combo;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.data.ICharmDataMap;
import net.sf.anathema.charms.tree.ICharmId;

public class DummyCharmDataMap implements ICharmDataMap {

  public final Map<ICharmId, CharmDto> dataById = new HashMap<ICharmId, CharmDto>();
  
  @Override
  public CharmDto getData(ICharmId charmId) {
    return dataById.get(charmId);
  }
}