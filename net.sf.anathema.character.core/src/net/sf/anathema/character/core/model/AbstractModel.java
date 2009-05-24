package net.sf.anathema.character.core.model;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.lib.control.ChangeManagement;
import net.sf.anathema.lib.control.change.ChangeControl;

public abstract class AbstractModel extends ChangeManagement implements IModel {

  private final ChangeControl changeControl = new ChangeControl();

  protected AbstractModel() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public void updateToDependencies() {
    // nothing to do
  }

  @Override
  public final void revertTo(Object saveState) {
    loadFromFromSaveState(saveState);
    fireChangedEvent();
    setClean();
  }

  protected abstract void loadFromFromSaveState(Object saveState);

  protected final void fireChangedEvent() {
    changeControl.fireChangedEvent();
  }

  @Override
  public final void addChangeListener(IChangeListener listener) {
    changeControl.addChangeListener(listener);
  }

  @Override
  public final void removeChangeListener(IChangeListener listener) {
    changeControl.removeChangeListener(listener);
  }
}