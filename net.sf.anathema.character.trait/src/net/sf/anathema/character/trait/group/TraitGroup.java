package net.sf.anathema.character.trait.group;

import net.disy.commons.core.util.ObjectUtilities;

public class TraitGroup implements ITraitGroup {

  private final String[] traitIds;
  private final String id;
  private final String label;

  public TraitGroup(String id, String label, String... traitIds) {
    this.id = id;
    this.label = label;
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
  public String getLabel() {
    return label;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ITraitGroup)) {
      return false;
    }
    ITraitGroup other = (ITraitGroup) obj;
    return ObjectUtilities.equals(getId(), other.getId());
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}