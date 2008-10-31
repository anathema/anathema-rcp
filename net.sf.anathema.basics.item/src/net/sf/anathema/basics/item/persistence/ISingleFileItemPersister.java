package net.sf.anathema.basics.item.persistence;

import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Document;

public interface ISingleFileItemPersister<D extends IItem> extends ISingleFileItemSaver<D> {

  public D load(Document document) throws PersistenceException;
}