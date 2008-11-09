package net.sf.anathema.basics.eclipse.extension;

import net.disy.commons.core.predicate.IPredicate;

public final class AttributePredicate implements IPredicate<IExtensionElement> {
  private final String treeId;
  private final String attributeName;

  public AttributePredicate(String attributeName, String treeId) {
    this.attributeName = attributeName;
    this.treeId = treeId;
  }

  @Override
  public boolean evaluate(IExtensionElement element) {
    return element.getAttribute(attributeName).equals(treeId);
  }
}