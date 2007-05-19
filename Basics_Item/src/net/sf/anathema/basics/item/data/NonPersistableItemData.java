package net.sf.anathema.basics.item.data;

import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public abstract class NonPersistableItemData implements IItemData {

  public void addDirtyListener(IChangeListener changeListener) {
    // nothing to do;
  }

  public boolean isDirty() {
    return false;
  }

  public void removeDirtyListener(IChangeListener changeListener) {
    // nothing to do;
  }

  public void setClean() {
    // nothing to do;
  }

  public void setPrintNameAdjuster(IObjectValueChangedListener<String> adjuster) {
    // nothing to do;
  }
}