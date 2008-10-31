package net.sf.anathema.lib.control;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.lib.control.change.ChangeControl;

public class ChangeManagement implements IChangeManagement {
  private boolean dirty = true;
  private final ChangeControl dirtyChangeControl = new ChangeControl();

  @Override
  public boolean isDirty() {
    return dirty;
  }

  @Override
  public void setClean() {
    setDirty(false);
  }

  public final void setDirty(boolean isDirty) {
    this.dirty = isDirty;
    fireDirtyChanged();
  }

  protected final void fireDirtyChanged() {
    dirtyChangeControl.fireChangedEvent();
  }

  @Override
  public void addDirtyListener(IChangeListener dirtyListener) {
    dirtyChangeControl.addChangeListener(dirtyListener);
  }

  @Override
  public void removeDirtyListener(IChangeListener dirtyListener) {
    dirtyChangeControl.removeChangeListener(dirtyListener);
  }

  @Override
  public int getDirtyListenerCount() {
    return dirtyChangeControl.getListenerCount();
  }
}