package net.sf.anathema.character.trait;

import net.sf.anathema.lib.control.IChangeManagement;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.ui.IDisposable;
import net.sf.anathema.lib.util.IIdentificate;

public interface IDisplayTrait extends IChangeManagement, IDisposable {

  public void addValueChangeListener(IChangeListener listener);

  public int getMaximalValue();
  
  public IIdentificate getTraitType();
  
  public int getValue();

  void removeValueChangeListener(IChangeListener listener);

  public void setValue(int value);
}