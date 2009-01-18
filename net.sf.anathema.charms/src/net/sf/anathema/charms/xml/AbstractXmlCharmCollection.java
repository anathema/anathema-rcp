package net.sf.anathema.charms.xml;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;
import org.eclipse.core.runtime.IContributor;

public abstract class AbstractXmlCharmCollection<C> implements Iterable<C> {

  private List<C> charms;
  private final IDocumentReader documentReader;
  private String resourcePath;

  public AbstractXmlCharmCollection(String resourcePath, IContributor contributor) {
    this(new ResourceDocumentReader(contributor, resourcePath));
    this.resourcePath = resourcePath;
  }

  public AbstractXmlCharmCollection(IDocumentReader documentReader) {
    this.documentReader = documentReader;
  }

  public final Iterator<C> iterator() {
    if (charms == null) {
      charms = readCharms();
    }
    return charms.iterator();
  }

  private List<C> readCharms() {
    List<C> newCharms = new ArrayList<C>();
    Element root = readDocument();
    for (Element charmElement : ElementUtilities.elements(root, "charm")) {
      newCharms.add(createCharm(charmElement));
    }
    return newCharms;
  }

  private Element readDocument() {
    try {
      return documentReader.readDocument();
    }
    catch (Exception e) {
      String filename = resourcePath == null ? "unknown document" : resourcePath;
      String format = MessageFormat.format("Error parsing {0}. Reason: {1}", filename, e.getMessage());
      throw new RuntimeException(format);
    }
  }

  protected abstract C createCharm(Element charmElement);
}