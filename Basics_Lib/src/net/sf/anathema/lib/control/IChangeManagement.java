package net.sf.anathema.lib.control;

import net.disy.commons.core.model.listener.IChangeListener;

public interface IChangeManagement {
  
  public boolean isDirty();

  public void setClean();

  public void removeDirtyListener(IChangeListener changeListener);

  public void addDirtyListener(IChangeListener changeListener);
  
  public int getDirtyListenerCount();
}