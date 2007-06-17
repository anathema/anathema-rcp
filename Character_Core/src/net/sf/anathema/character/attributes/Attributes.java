package net.sf.anathema.character.attributes;

import net.sf.anathema.character.trait.ITrait;
import net.sf.anathema.lib.control.AggregatedChangeManagement;

public class Attributes extends AggregatedChangeManagement implements IAttributes {

  private final ITrait[] traits;

  public Attributes(ITrait... traits) {
    this.traits = traits;
    setChangeManagments(traits);
  }

  @Override
  public ITrait[] getTraits() {
    return traits;
  }

  @Override
  public ITrait getTrait(String id) {
    for (ITrait trait : traits) {
      if (id.equals(trait.getTraitType().getId())) {
        return trait;
      }
    }
    throw new IllegalArgumentException("No trait found for id " + id);
  }
}