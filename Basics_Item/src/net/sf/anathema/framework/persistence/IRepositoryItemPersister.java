package net.sf.anathema.framework.persistence;

import net.sf.anathema.framework.item.IItem;
import net.sf.anathema.framework.item.data.IItemData;
import net.sf.anathema.lib.exception.PersistenceException;

public interface IRepositoryItemPersister<D extends IItemData> {

  public IItem<D> createNew(IAnathemaWizardModelTemplate template) throws PersistenceException;
}