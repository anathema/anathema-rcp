package net.sf.anathema.character.trait;

import net.sf.anathema.lib.control.IChangeManagement;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.ui.IDisposable;
import net.sf.anathema.lib.util.IIdentificate;

public interface IDisplayTrait extends IChangeManagement, IDisposable {

  public int getValue();

  public void setValue(int value);

  public int getMaximalValue();

  public IIdentificate getTraitType();

  public void addValueChangeListener(IChangeListener listener);

  public void removeValueChangeListener(IChangeListener listener);
}