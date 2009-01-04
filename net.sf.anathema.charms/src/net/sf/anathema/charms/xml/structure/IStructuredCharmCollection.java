package net.sf.anathema.charms.xml.structure;

public interface IStructuredCharmCollection extends Iterable<IStructuredCharm> {

  public IStructuredCharm getCharmForTreeId(String id);
}