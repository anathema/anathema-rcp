package net.sf.anathema.charms.xml.structure;

import net.sf.anathema.charms.xml.AbstractXmlCharmCollection;

import org.dom4j.Element;
import org.eclipse.core.runtime.IContributor;

public class StructuredCharmCollection extends AbstractXmlCharmCollection<IStructuredCharm> implements
    IStructuredCharmCollection {

  public StructuredCharmCollection(String resourcePath, IContributor contributor) {
    super(resourcePath, contributor);
  }

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
  protected StructuredCharm createCharm(Element charmElement) {
    return new StructuredCharm(charmElement);
  }
}