package net.sf.anathema.framework.repository;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.framework.item.PrintNameAdjuster;
import net.sf.anathema.framework.item.data.IItemData;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.util.IIdentificate;

public class AnathemaDataItem<D extends IItemData> extends AbstractAnathemaItem<D> {

  private final D itemData;

  public AnathemaDataItem(D itemData) {
    Ensure.ensureArgumentNotNull("Use AnathemaNullDataItem for items without data.", itemData); //$NON-NLS-1$
    this.itemData = itemData;
    itemData.setPrintNameAdjuster(new PrintNameAdjuster(this));
  }

  public AnathemaDataItem(IIdentificate identificate, D itemData) {
    super(identificate);
    Ensure.ensureArgumentNotNull("Use AnathemaNullDataItem for items without data.", itemData); //$NON-NLS-1$
    this.itemData = itemData;
    itemData.setPrintNameAdjuster(new PrintNameAdjuster(this));
  }

  public D getItemData() {
    return itemData;
  }

  public boolean isDirty() {
    return itemData.isDirty();
  }

  public void setClean() {
    itemData.setClean();
  }

  public void addDirtyListener(IChangeListener changeListener) {
    itemData.addDirtyListener(changeListener);
  }

  public void removeDirtyListener(IChangeListener changeListener) {
    itemData.removeDirtyListener(changeListener);
  }
}