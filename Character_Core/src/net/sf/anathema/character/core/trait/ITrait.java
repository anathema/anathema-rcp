package net.sf.anathema.character.core.trait;

import net.sf.anathema.lib.control.IChangeManagement;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface ITrait extends IChangeManagement, IBasicTrait {

  public void addValueChangeListener(IChangeListener listener);
  
  public int getValue();
  
  public void setValue(int value);

  public int getMaximalValue();
}