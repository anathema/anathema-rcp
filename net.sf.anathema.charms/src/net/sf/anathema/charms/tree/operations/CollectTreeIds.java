package net.sf.anathema.charms.tree.operations;

import java.util.Set;

import net.disy.commons.core.util.IClosure;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.charms.tree.CharmTreeExtensionPoint;

public final class CollectTreeIds implements IClosure<IExtensionElement> {
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private static final String TAG_TREE = "tree"; //$NON-NLS-1$
  private final Set<String> set;

  public CollectTreeIds(Set<String> set) {
    this.set = set;
  }

  @Override
  public void execute(IExtensionElement element) throws RuntimeException {
    if (element.getName().equals(CharmTreeExtensionPoint.TAG_TREEPART)) {
      set.add(element.getAttribute(CharmTreeExtensionPoint.ATTRIB_TREE_REFERENCE));
    }
    if (element.getName().equals(TAG_TREE)) {
      set.add(element.getAttribute(ATTRIB_ID));
    }
  }
}