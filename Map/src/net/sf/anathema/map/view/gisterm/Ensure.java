package net.sf.anathema.map.view.gisterm;

public class Ensure {

  public static void ensureArgumentNotNull(Object object) {
    if (object == null) {
      throw new IllegalArgumentException("Object must not be null"); //$NON-NLS-1$
    }
  }
}