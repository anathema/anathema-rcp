package net.sf.anathema.framework.item.persistence;

import net.sf.anathema.framework.item.IItem;
import net.sf.anathema.framework.item.data.IBasicItemData;
import net.sf.anathema.framework.item.data.IItemData;

public interface IRepositoryItemPersister<D extends IItemData> {

  public IItem<IBasicItemData> createNew();
}