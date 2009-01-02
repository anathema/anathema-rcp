package net.sf.anathema.charms.data;

import java.util.HashMap;
import java.util.Map;

public class DummyNameMap implements INameMap {

  private final Map<String, String> map = new HashMap<String, String>();

  public void put(String string, String string2) {
    map.put(string, string2);
  }

  public String getNameFor(String id) {
    return map.get(id);
  }
}