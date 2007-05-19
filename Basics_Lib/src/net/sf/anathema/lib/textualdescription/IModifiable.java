package net.sf.anathema.lib.textualdescription;

import net.sf.anathema.lib.control.change.IChangeListener;

public interface IModifiable {

  public boolean isDirty();

  public void setClean();

  public void addDirtyChangeListener(IChangeListener dirtyListener);

  public void removeDirtyChangeListener(IChangeListener dirtyListener);
}