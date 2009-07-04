package net.sf.anathema.charms.tree.operations;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.charms.extension.tree.CharmTreeExtensionPoint;

public final class ForTreePart implements IPredicate<IExtensionElement> {
  private final String id;

  public ForTreePart(String id) {
    this.id = id;
  }

  @Override
  public boolean evaluate(IExtensionElement element) {
    return element.getName().equals(CharmTreeExtensionPoint.TAG_TREEPART) && element.getAttribute(CharmTreeExtensionPoint.ATTRIB_TREE_REFERENCE).equals(id);
  }
}