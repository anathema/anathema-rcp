package net.sf.anathema.lib.util;

import net.disy.commons.core.util.ObjectUtilities;

public class Identificate implements IIdentificate {

  private final String id;

  public Identificate(String id) {
    this.id = id;
  }

  public final String getId() {
    return id;
  }

  @Override
  public String toString() {
    return id;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Identificate)) {
      return false;
    }
    Identificate other = (Identificate) obj;
    return ObjectUtilities.equals(other.id, id);
  }

  @Override
  public int hashCode() {
    return id == null ? -1 : getId().hashCode();
  }
}