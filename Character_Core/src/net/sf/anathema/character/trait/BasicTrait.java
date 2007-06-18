package net.sf.anathema.character.trait;

import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.util.IIdentificate;

public class BasicTrait implements IBasicTrait {
  
  private final ChangeControl creationChange = new ChangeControl();
  private final ChangeControl experiencedChange = new ChangeControl();
  private final IIdentificate traitType;
  private int creationValue = 0;
  private int experiencedValue = -1;

  public BasicTrait(IIdentificate traitType) {
    this.traitType = traitType;
  }

  @Override
  public int getCreationValue() {
    return creationValue;
  }

  @Override
  public int getExperiencedValue() {
    return experiencedValue;
  }

  @Override
  public IIdentificate getTraitType() {
    return traitType;
  }

  @Override
  public void addCreationChangeListener(IChangeListener changeListener) {
    creationChange.addChangeListener(changeListener);
  }

  @Override
  public void addExperienceChangeListener(IChangeListener changeListener) {
    experiencedChange.addChangeListener(changeListener);
  }

  @Override
  public void removeCreationChangeListener(IChangeListener changeListener) {
    creationChange.removeChangeListener(changeListener);
  }

  @Override
  public void removeExperienceChangeListener(IChangeListener changeListener) {
    experiencedChange.removeChangeListener(changeListener);
  }

  @Override
  public void setCreationValue(int value) {
    if (this.creationValue == value) {
      return;
    }
    this.creationValue = value;
    creationChange.fireChangedEvent();
  }

  @Override
  public void setExperiencedValue(int value) {
    if (this.experiencedValue == value) {
      return;
    }
    this.experiencedValue = value;
    experiencedChange.fireChangedEvent();
  }
}