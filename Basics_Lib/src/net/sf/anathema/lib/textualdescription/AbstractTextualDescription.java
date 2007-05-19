package net.sf.anathema.lib.textualdescription;

import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;

public abstract class AbstractTextualDescription implements ITextualDescription {

  private boolean dirty = true;
  private final ChangeControl dirtyChangeControl = new ChangeControl();

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

  public void addDirtyChangeListener(IChangeListener dirtyListener) {
    dirtyChangeControl.addChangeListener(dirtyListener);
  }
  
  public void removeDirtyChangeListener(IChangeListener dirtyListener) {
    dirtyChangeControl.removeChangeListener(dirtyListener);
  }
}