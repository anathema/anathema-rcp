package net.sf.anathema.character.core.fake;

import net.sf.anathema.character.core.model.AbstractModel;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public class DummyModel extends AbstractModel {
  private ChangeControl control = new ChangeControl();

  @Override
  public void addChangeListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }

  @Override
  public void removeChangeListener(IChangeListener listener) {
    control.removeChangeListener(listener);
  }
  
  public void fireModelChanged() {
    control.fireChangedEvent();
  }
}