package net.sf.anathema.charms.xml;

public interface IXmlCharmCollection extends Iterable<IXmlCharm> {

  public IXmlCharm getCharmForTreeId(String id);
}