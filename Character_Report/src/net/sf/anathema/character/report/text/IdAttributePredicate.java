/**
 * 
 */
package net.sf.anathema.character.report.text;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

public final class IdAttributePredicate implements IPredicate<IExtensionElement> {
  private final String id;

  public IdAttributePredicate(String id) {
    this.id = id;
  }

  @Override
  public boolean evaluate(IExtensionElement element) {
    return element.getAttribute("id").equals(id); //$NON-NLS-1$
  }
}