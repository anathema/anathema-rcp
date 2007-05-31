package net.sf.anathema.campaign.plot.repository;

import org.eclipse.osgi.util.NLS;

public class EnumInternationalizer {

  private final Class< ? > clazz;

  public <M extends NLS> EnumInternationalizer(Class<M> clazz) {
    this.clazz = clazz;
  }

  public String getDisplayName(Enum< ? > enumObject) {
    try {
      return (String) clazz.getField(enumObject.getDeclaringClass().getSimpleName() + "_" + enumObject.name()).get(null); //$NON-NLS-1$
    }
    catch (IllegalArgumentException e) {
      throw new IllegalStateException("Failed to internationalize " + enumObject.name(), e); //$NON-NLS-1$
    }
    catch (SecurityException e) {
      throw new IllegalStateException("Failed to internationalize " + enumObject.name(), e); //$NON-NLS-1$
    }
    catch (IllegalAccessException e) {
      throw new IllegalStateException("Failed to internationalize " + enumObject.name(), e); //$NON-NLS-1$
    }
    catch (NoSuchFieldException e) {
      throw new IllegalStateException("Failed to internationalize " + enumObject.name(), e); //$NON-NLS-1$
    }
  }
}
