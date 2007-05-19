package net.sf.anathema.framework.persistence;

import net.sf.anathema.framework.item.IItem;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;
import org.eclipse.core.runtime.IExecutableExtension;

public interface IItemPersister extends IExecutableExtension {

  public void save(Element element, IItem< ? > item);

  public void load(Element element, IItem< ? > item) throws PersistenceException;
}