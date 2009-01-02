package net.sf.anathema.charms.data;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.charms.tree.DummyCharmId;
import net.sf.anathema.charms.tree.ICharmId;

public class DummyNameMap implements INameMap {

  private final Map<ICharmId, String> map = new HashMap<ICharmId, String>();

  public void put(String id, String string2) {
    map.put(new DummyCharmId(id), string2);
  }

  public String getNameFor(ICharmId id) {
    return map.get(id);
  }
}