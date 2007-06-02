package net.sf.anathema.lib.control;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.lib.control.change.IChangeListener;

public class AggregatedChangeManagement implements IChangeManagement {

  private IChangeManagement[] changeManagements;

  @Override
  public final void addDirtyListener(IChangeListener changeListener) {
    for (IChangeManagement changeManagement : changeManagements) {
      changeManagement.addDirtyListener(changeListener);
    }
  }

  @Override
  public final boolean isDirty() {
    for (IChangeManagement changeManagement : changeManagements) {
      if (changeManagement.isDirty()) {
        return true;
      }
    }
    return false;
  }

  @Override
  public final void removeDirtyListener(IChangeListener changeListener) {
    for (IChangeManagement changeManagement : changeManagements) {
      changeManagement.removeDirtyListener(changeListener);
    }
  }

  @Override
  public final void setClean() {
    for (IChangeManagement changeManagement : changeManagements) {
      changeManagement.setClean();
    }
  }

  protected void setChangeManagments(IChangeManagement... changeManagements) {
    Ensure.ensureNull(this.changeManagements);
    this.changeManagements = changeManagements;
  }
}