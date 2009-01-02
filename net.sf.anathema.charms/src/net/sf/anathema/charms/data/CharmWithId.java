package net.sf.anathema.charms.data;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

public final class CharmWithId implements IPredicate<IExtensionElement> {
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private final String charmId;

  public CharmWithId(String charmId) {
    this.charmId = charmId;
  }

  public boolean evaluate(IExtensionElement element) {
    return element.getAttribute(ATTRIB_ID).equals(charmId) ;
  }
}