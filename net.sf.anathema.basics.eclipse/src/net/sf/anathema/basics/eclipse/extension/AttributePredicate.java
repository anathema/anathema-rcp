package net.sf.anathema.basics.eclipse.extension;

import net.disy.commons.core.predicate.IPredicate;

public final class AttributePredicate implements IPredicate<IExtensionElement> {

  public static AttributePredicate FromNameAndValue(String name, String value) {
    return new AttributePredicate(name, value);
  }

  private final String value;
  private final String name;

  private AttributePredicate(String name, String value) {
    this.name = name;
    this.value = value;
  }

  @Override
  public boolean evaluate(IExtensionElement element) {
    return element.getAttribute(name).equals(value);
  }
}