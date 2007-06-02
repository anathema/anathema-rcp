package net.sf.anathema.basics.item.persistence;

import net.sf.anathema.basics.item.data.IBasicItemData;
import net.sf.anathema.basics.item.data.IItemData;

public interface IRepositoryItemPersister<D extends IItemData> {

  public IBasicItemData createNew();
}