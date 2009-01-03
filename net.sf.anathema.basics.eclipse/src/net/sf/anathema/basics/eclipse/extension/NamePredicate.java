package net.sf.anathema.basics.eclipse.extension;

import net.disy.commons.core.predicate.IPredicate;

public final class NamePredicate implements IPredicate<IExtensionElement> {

  public static NamePredicate ForElementName(String name) {
    return new NamePredicate(name);
  }

  private final String name;

  private NamePredicate(String name) {
    this.name = name;
  }

  @Override
  public boolean evaluate(IExtensionElement element) {
    return element.getName().equals(name);
  }
}