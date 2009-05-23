package net.sf.anathema.charms.character.model;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

public final class IsVirtualCharmWithIdPattern implements IPredicate<IExtensionElement> {
  private final String pattern;

  public IsVirtualCharmWithIdPattern(String pattern) {
    this.pattern = pattern;
  }

  @Override
  public boolean evaluate(IExtensionElement element) {
    return element.getName().endsWith(VirtualCharmEvaluation.TAG_VIRTUAL_CHARM)
        && pattern.equals(element.getAttribute(VirtualCharmEvaluation.ATTRIB_CHARM_ID_PATTERN));
  }
}