package net.sf.anathema.charms.tree.operations;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

public final class ForGenerics implements IPredicate<IExtensionElement> {
  private static final String TAG_GENERIC_CHARMS = "genericCharms"; //$NON-NLS-1$
  private static final String ATTRIB_CHARACTERTYPE = "characterType"; //$NON-NLS-1$
  private final String characterType;

  public ForGenerics(String characterType) {
    this.characterType = characterType;
  }

  @Override
  public boolean evaluate(IExtensionElement element) {
    return element.getName().equals(TAG_GENERIC_CHARMS) && element.getAttribute(ATTRIB_CHARACTERTYPE).equals(characterType);
  }
}