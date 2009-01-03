package net.sf.anathema.charms.xml;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.disy.commons.core.io.IOUtilities;
import net.sf.anathema.basics.eclipse.resource.ResourceUtils;
import net.sf.anathema.lib.xml.DocumentUtilities;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;
import org.eclipse.core.runtime.IContributor;

public class XmlCharmCollection implements IXmlCharmCollection {

  private final IContributor contributor;
  private final String resourcePath;

  public XmlCharmCollection(String resourcePath, IContributor contributor) {
    this.resourcePath = resourcePath;
    this.contributor = contributor;
  }

  @Override
  public IXmlCharm getCharmForTreeId(String id) {
    for (IXmlCharm charm : this) {
      if (charm.getTreePart().equals(id)) {
        return charm;
      }
    }
    return null;
  }

  private Element readDocument() throws Exception {
    URL resourceUrl = ResourceUtils.getResourceUrl(contributor.getName(), resourcePath);
    InputStream inputStream = null;
    try {
      inputStream = resourceUrl.openStream();
      return DocumentUtilities.read(inputStream).getDocument().getRootElement();
    }
    finally {
      IOUtilities.close(inputStream);
    }
  }

  @Override
  public Iterator<IXmlCharm> iterator() {
    return readCharms().iterator();
  }

  private List<IXmlCharm> readCharms() {
    List<IXmlCharm> charms = new ArrayList<IXmlCharm>();
    try {
      Element root = readDocument();
      for (Element charmElement : ElementUtilities.elements(root, "charm")) {
        charms.add(new XmlCharm(charmElement));
      }
    }
    catch (Exception e) {
      // TODO Fehlerhandling
    }
    return charms;
  }
}