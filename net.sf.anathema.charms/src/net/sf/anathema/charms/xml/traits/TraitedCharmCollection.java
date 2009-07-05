package net.sf.anathema.charms.xml.traits;

import net.sf.anathema.charms.xml.AbstractXmlCharmCollection;

import org.dom4j.Element;
import org.eclipse.core.runtime.IContributor;

public class TraitedCharmCollection extends AbstractXmlCharmCollection<ITraitedCharm> {

  public TraitedCharmCollection(String resource, IContributor contributor) {
    super(resource, contributor);
  }

  @Override
  protected ITraitedCharm createCharm(Element charmElement) {
    return new TraitedCharm(charmElement);
  }
}