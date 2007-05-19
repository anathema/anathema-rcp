package net.sf.anathema.framework.item;

import net.sf.anathema.framework.item.change.IChangeManagement;
import net.sf.anathema.framework.item.data.IItemData;

public interface IItem<D extends IItemData> extends IChangeManagement {

  public static final String DEFAULT_PRINT_NAME = "Unnamed"; //$NON-NLS-1$

  public D getItemData();

  public String getPrintName();

  public void setPrintName(String printName);

  public void addItemListener(IItemListener listener);

  public void removeItemListener(IItemListener listener);
}