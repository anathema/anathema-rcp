package net.sf.anathema.basics.item.persistence;

import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.basics.item.data.ITitledText;

public interface IRepositoryItemPersister<D extends IItem> {

  public ITitledText createNew();
}