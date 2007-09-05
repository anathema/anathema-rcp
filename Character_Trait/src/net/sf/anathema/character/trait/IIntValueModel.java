package net.sf.anathema.character.trait;

import net.disy.commons.core.model.listener.IChangeListener;

public interface IIntValueModel {

  public int getValue();

  public void setValue(int value);

  public void addValueChangeListener(IChangeListener changeListener);

  public void removeValueChangeListener(IChangeListener changeListener);
}