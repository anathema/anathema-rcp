package net.sf.anathema.charms.character;

import net.disy.commons.core.creation.IFactory;

public class StaticFactory<T, E extends Exception> implements IFactory<T, E> {

  private final T object;

  public StaticFactory(T object) {
    this.object = object;
  }
  
  @Override
  public T createInstance() throws E {
    return object;
  }
}