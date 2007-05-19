package net.sf.anathema.framework.item.role;

import net.sf.anathema.framework.item.IItem;
import net.sf.anathema.framework.item.data.IItemData;

public interface IItemRole<D extends IItemData> extends IItem<D> {

  public void setBase(IItem<D> item);
}