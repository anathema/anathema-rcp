package net.sf.anathema.charms.xml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DummyXmlCharmCollection implements IXmlCharmCollection {

  private final List<IXmlCharm> allCharms = new ArrayList<IXmlCharm>();

  @Override
  public IXmlCharm getCharmForTreeId(String id) {
    for (IXmlCharm charm : this) {
      if (charm.getTreePart().equals(id)) {
        return charm;
      }
    }
    return null;
  }

  @Override
  public Iterator<IXmlCharm> iterator() {
    return allCharms.iterator();
  }

  public void add(IXmlCharm charm) {
    allCharms.add(charm);
  }
}