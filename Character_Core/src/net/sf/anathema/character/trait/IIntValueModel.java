package net.sf.anathema.character.trait;

import net.sf.anathema.lib.control.change.IChangeListener;

public interface IIntValueModel {

  public int getValue();

  public void setValue(int value);

  public void addChangeListener(IChangeListener changeListener);

  public void removeChangeListener(IChangeListener changeListener);
}