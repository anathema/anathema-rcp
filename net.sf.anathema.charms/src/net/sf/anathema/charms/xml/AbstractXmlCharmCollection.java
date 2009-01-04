package net.sf.anathema.charms.xml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;
import org.eclipse.core.runtime.IContributor;

public abstract class AbstractXmlCharmCollection<C> implements Iterable<C> {

  private final IContributor contributor;
  private final String resourcePath;
  private List<C> charms;

  public AbstractXmlCharmCollection(String resourcePath, IContributor contributor) {
    this.resourcePath = resourcePath;
    this.contributor = contributor;
  }

  private Element readDocument() throws Exception {
    return new ResourceDocumentReader(contributor, resourcePath).readDocument();
  }

  public final Iterator<C> iterator() {
    if (charms == null) {
      charms = readCharms();
    }
    return charms.iterator();
  }

  private List<C> readCharms() {
    List<C> newCharms = new ArrayList<C>();
    try {
      Element root = readDocument();
      for (Element charmElement : ElementUtilities.elements(root, "charm")) {
        newCharms.add(createCharm(charmElement));
      }
    }
    catch (Exception e) {
      // TODO Fehlerhandling
    }
    return newCharms;
  }

  protected abstract C createCharm(Element charmElement);
}