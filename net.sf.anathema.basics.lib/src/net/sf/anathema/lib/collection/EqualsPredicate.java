package net.sf.anathema.lib.collection;

import net.disy.commons.core.predicate.IPredicate;

public class EqualsPredicate<T> implements IPredicate<T> {

  private final T value;

  public EqualsPredicate(T value) {
    this.value = value;
  }

  @Override
  public boolean evaluate(T input) {
    return value.equals(input);
  }
}