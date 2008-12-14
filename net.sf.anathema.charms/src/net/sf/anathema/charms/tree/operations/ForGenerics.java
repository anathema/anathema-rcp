package net.sf.anathema.charms.tree.operations;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

public final class ForGenerics implements IPredicate<IExtensionElement> {
  private static final String TAG_GENERIC_CHARMS = "genericCharms"; //$NON-NLS-1$

  @Override
  public boolean evaluate(IExtensionElement element) {
    return element.getName().equals(TAG_GENERIC_CHARMS);
  }
}