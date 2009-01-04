package net.sf.anathema.charms.xml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.anathema.charms.xml.structure.IStructuredCharm;
import net.sf.anathema.charms.xml.structure.IStructuredCharmCollection;

public class DummyXmlCharmCollection implements IStructuredCharmCollection {

  private final List<IStructuredCharm> allCharms = new ArrayList<IStructuredCharm>();

  @Override
  public IStructuredCharm getCharmForTreeId(String id) {
    for (IStructuredCharm charm : this) {
      if (charm.getTreePart().equals(id)) {
        return charm;
      }
    }
    return null;
  }

  @Override
  public Iterator<IStructuredCharm> iterator() {
    return allCharms.iterator();
  }

  public void add(IStructuredCharm charm) {
    allCharms.add(charm);
  }
}