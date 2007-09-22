package net.sf.anathema.lib.ui;

import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public interface IIntValueView {
  
  public void setValue(int newValue);

  public void addIntValueChangedListener(IIntValueChangedListener listener);

  public void removeIntValueChangedListener(IIntValueChangedListener listener);
}