package net.sf.anathema.character.trait;

import net.sf.anathema.lib.control.change.IChangeListener;

public interface IIntValueModel {

  public int getValue();

  public void setValue(int value);

  public void addValueChangeListener(IChangeListener changeListener);

  public void removeValueChangeListener(IChangeListener changeListener);
}