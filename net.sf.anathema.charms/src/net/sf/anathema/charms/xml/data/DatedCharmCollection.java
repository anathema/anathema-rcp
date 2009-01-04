package net.sf.anathema.charms.xml.data;

import net.sf.anathema.charms.xml.AbstractXmlCharmCollection;

import org.dom4j.Element;
import org.eclipse.core.runtime.IContributor;

public class DatedCharmCollection extends AbstractXmlCharmCollection<IDatedCharm> implements IDatedCharmCollection {

  public DatedCharmCollection(String resourcePath, IContributor contributor) {
    super(resourcePath, contributor);
  }

  @Override
  protected IDatedCharm createCharm(Element charmElement) {
    return new DatedCharm(charmElement);
  }
}