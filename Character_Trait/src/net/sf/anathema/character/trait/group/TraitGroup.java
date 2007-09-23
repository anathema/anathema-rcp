package net.sf.anathema.character.trait.group;

import com.sun.corba.se.impl.orbutil.ObjectUtility;

public class TraitGroup implements ITraitGroup {

  private final String[] traitIds;
  private final String id;

  public TraitGroup(String id, String... traitIds) {
    this.id = id;
    this.traitIds = traitIds;
  }

  public String[] getTraitIds() {
    return traitIds;
  }

  @Override
  public String getId() {
    return id;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ITraitGroup)) {
      return false;
    }
    ITraitGroup other = (ITraitGroup) obj;
    return ObjectUtility.equals(getId(), other.getId());
  }
  
  @Override
  public int hashCode() {
    return id.hashCode();
  }
}