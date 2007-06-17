package net.sf.anathema.character.trait;

import net.sf.anathema.lib.control.ChangeManagement;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.util.IIdentificate;

public class Trait extends ChangeManagement implements ITrait {

  private final IIdentificate traitType;
  private int value;
  private ChangeControl changeControl = new ChangeControl();

  public Trait(IIdentificate traitType) {
    this.traitType = traitType;
  }

  @Override
  public int getValue() {
    return value;
  }

  @Override
  public int getMaximalValue() {
    return 5;
  }

  @Override
  public IIdentificate getTraitType() {
    return traitType;
  }

  @Override
  public void addValueChangeListener(IChangeListener listener) {
    changeControl.addChangeListener(listener);
  }

  @Override
  public void setValue(int value) {
    if (this.value == value) {
      return;
    }
    this.value = value;
    setDirty(true);
    changeControl.fireChangedEvent();
  }
}