package net.sf.anathema.basics.item.data;

import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public class BasicItemData implements IBasicItemData {

  private final IItemDescription description = new ItemDescription();

  public void setPrintNameAdjuster(IObjectValueChangedListener<String> adjuster) {
    description.getName().addTextChangedListener(adjuster);
  }

  public IItemDescription getDescription() {
    return description;
  }

  public boolean isDirty() {
    return description.isDirty();
  }

  public void setClean() {
    description.setClean();
  }

  public void addDirtyListener(IChangeListener changeListener) {
    description.addDirtyListener(changeListener);
  }

  public void removeDirtyListener(IChangeListener changeListener) {
    description.removeDirtyListener(changeListener);
  }
}