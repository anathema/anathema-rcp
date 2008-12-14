package net.sf.anathema.charms.tree;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

public final class TreeWithId implements IPredicate<IExtensionElement> {
  private final String id;

  public TreeWithId(String id) {
    this.id = id;
  }

  @Override
  public boolean evaluate(IExtensionElement element) {
    return element.getName().equals("tree") && id.equals(element.getAttribute("id"));
  }
}