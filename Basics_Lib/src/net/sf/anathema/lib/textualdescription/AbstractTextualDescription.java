package net.sf.anathema.lib.textualdescription;

import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public abstract class AbstractTextualDescription implements ITextualDescription {

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

  protected final void setDirty(boolean isDirty) {
    this.dirty = isDirty;
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
}