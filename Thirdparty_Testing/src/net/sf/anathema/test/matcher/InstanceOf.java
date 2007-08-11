package net.sf.anathema.test.matcher;

import org.easymock.IArgumentMatcher;

public class InstanceOf implements IArgumentMatcher {

  private final Class< ? > clazz;
  private Object lastActual;

  public InstanceOf(Class< ? > clazz) {
    this.clazz = clazz;
  }

  public boolean matches(Object actual) {
    this.lastActual = actual;
    return (actual != null) && clazz.isAssignableFrom(actual.getClass());
  }

  public Object getLastActual() {
    return lastActual;
  }

  public void appendTo(StringBuffer buffer) {
    buffer.append("isA(" + clazz.getName() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
  }
}