package net.sf.anathema.charms.tree.operations;

import java.util.Set;

import net.disy.commons.core.util.IClosure;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.charms.tree.CharmTreeExtensionPoint;

public final class CollectTreeIds implements IClosure<IExtensionElement> {
  private final Set<String> set;

  public CollectTreeIds(Set<String> set) {
    this.set = set;
  }

  @Override
  public void execute(IExtensionElement element) throws RuntimeException {
    if (element.getName().equals(CharmTreeExtensionPoint.TAG_TREEPART)) {
      set.add(element.getAttribute(CharmTreeExtensionPoint.ATTRIB_TREE_REFERENCE));
    }
    if (element.getName().equals("tree")) {
      set.add(element.getAttribute("id"));
    }
  }
}