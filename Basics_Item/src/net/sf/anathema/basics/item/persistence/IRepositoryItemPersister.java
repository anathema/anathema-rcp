package net.sf.anathema.basics.item.persistence;

import net.sf.anathema.basics.item.IItem;

public interface IRepositoryItemPersister<D extends IItem> {

  public D createNew();
}