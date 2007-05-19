package net.sf.anathema.framework.item.persistence;

import java.io.IOException;
import java.io.OutputStream;

import net.sf.anathema.framework.item.IItem;
import net.sf.anathema.framework.item.data.IItemData;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Document;

public interface ISingleFileItemPersister<D extends IItemData> extends IRepositoryItemPersister<D> {

  public abstract void save(OutputStream stream, IItem<D> item) throws IOException, PersistenceException;

  public abstract IItem<D> load(Document document) throws PersistenceException;
}