package net.sf.anathema.framework.item.persistence;

import net.sf.anathema.framework.item.IItem;
import net.sf.anathema.framework.item.data.IItemData;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;
import org.eclipse.core.runtime.IExecutableExtension;

public interface IItemPersister extends IExecutableExtension {

  public <D extends IItemData> void save(Element element, IItem<D> item);

  public <D extends IItemData> void load(Element element, IItem<D> item) throws PersistenceException;
}