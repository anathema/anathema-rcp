package net.sf.anathema.charms.xml.data;

import java.util.Properties;

import net.sf.anathema.charms.xml.AbstractXmlCharmCollection;
import net.sf.anathema.charms.xml.IDocumentReader;

import org.dom4j.Element;
import org.eclipse.core.runtime.IContributor;

public class DatedCharmCollection extends AbstractXmlCharmCollection<IDatedCharm> implements IDatedCharmCollection {

  private Properties pages;

  public DatedCharmCollection(String resourcePath, IContributor contributor) {
    super(resourcePath, contributor);
  }

  public DatedCharmCollection(IDocumentReader documentReader) {
    super(documentReader);
  }

  @Override
  protected IDatedCharm createCharm(Element charmElement) {
    return new DatedCharm(charmElement);
  }
}