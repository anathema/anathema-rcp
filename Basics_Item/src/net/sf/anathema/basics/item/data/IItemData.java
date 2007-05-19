package net.sf.anathema.basics.item.data;

import net.sf.anathema.basics.item.change.IChangeManagement;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public interface IItemData extends IChangeManagement {

  public void setPrintNameAdjuster(IObjectValueChangedListener<String> adjuster);
}