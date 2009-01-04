package net.sf.anathema.charms.tree;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

public final class TreeWithId implements IPredicate<IExtensionElement> {
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private static final String NAME_TREE = "tree"; //$NON-NLS-1$
  private final String id;

  public TreeWithId(String id) {
    this.id = id;
  }

  @Override
  public boolean evaluate(IExtensionElement element) {
    return element.getName().equals(NAME_TREE) && id.equals(element.getAttribute(ATTRIB_ID));
  }
}