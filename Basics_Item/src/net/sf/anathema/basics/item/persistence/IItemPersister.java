package net.sf.anathema.basics.item.persistence;

import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;
import org.eclipse.core.runtime.IExecutableExtension;

public interface IItemPersister extends IExecutableExtension {

  public <D extends IItem> void save(Element element, D item);

  public <D extends IItem> void load(Element element, D item) throws PersistenceException;
}