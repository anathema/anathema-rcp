/**
 * 
 */
package net.sf.anathema.basics.eclipse.runtime;

public final class StaticProvider<T> implements IProvider<T> {
  private final T object;

  public StaticProvider(T object) {
    this.object = object;
  }

  @Override
  public T get() {
    return object;
  }
}