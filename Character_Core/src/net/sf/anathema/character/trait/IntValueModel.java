package net.sf.anathema.character.trait;

import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class IntValueModel implements IIntValueModel {

  private final ChangeControl control = new ChangeControl();
  private int value;

  @Override
  public void addChangeListener(IChangeListener changeListener) {
    control.addChangeListener(changeListener);

  }

  @Override
  public int getValue() {
    return value;
  }

  @Override
  public void removeChangeListener(IChangeListener changeListener) {
    control.removeChangeListener(changeListener);

  }

  @Override
  public void setValue(int value) {
    if (this.value == value) {
      return;
    }
    this.value = value;
    control.fireChangedEvent();
  }
}