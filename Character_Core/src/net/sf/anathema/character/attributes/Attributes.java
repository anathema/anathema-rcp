package net.sf.anathema.character.attributes;

import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.lib.control.ChangeManagement;
import net.sf.anathema.lib.control.change.IChangeListener;

public class Attributes extends ChangeManagement implements IAttributes {

  private final IBasicTrait[] traits;
  private final IChangeListener dirtyListener = new IChangeListener() {
    @Override
    public void changeOccured() {
      setDirty(true);
    }
  };

  public Attributes(IBasicTrait... traits) {
    this.traits = traits;
    for (IBasicTrait basicTrait : traits) {
      basicTrait.addCreationChangeListener(dirtyListener);
      basicTrait.addExperienceChangeListener(dirtyListener);
    }
  }

  @Override
  public IBasicTrait[] getTraits() {
    return traits;
  }

  @Override
  public IBasicTrait getTrait(String id) {
    for (IBasicTrait trait : traits) {
      if (id.equals(trait.getTraitType().getId())) {
        return trait;
      }
    }
    throw new IllegalArgumentException("No trait found for id " + id);
  }
}