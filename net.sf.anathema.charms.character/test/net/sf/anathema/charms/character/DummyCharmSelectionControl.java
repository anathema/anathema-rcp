package net.sf.anathema.charms.character;

import net.sf.anathema.charms.view.ICharmSelectionControl;
import net.sf.anathema.charms.view.ICharmSelectionListener;
import net.sf.anathema.lib.control.GenericControl;

public class DummyCharmSelectionControl implements ICharmSelectionControl {

  public final GenericControl<ICharmSelectionListener> control = new GenericControl<ICharmSelectionListener>(); 
  
  @Override
  public void addSelectionListener(ICharmSelectionListener listener) {
    control.addListener(listener);
  }

  @Override
  public void removeSelectionListener(ICharmSelectionListener selectionListener) {
    control.removeListener(selectionListener);
  }
}