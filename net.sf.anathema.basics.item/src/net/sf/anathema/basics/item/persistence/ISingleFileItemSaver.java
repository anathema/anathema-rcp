package net.sf.anathema.basics.item.persistence;

import java.io.IOException;
import java.io.OutputStream;

import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.lib.exception.PersistenceException;

public interface ISingleFileItemSaver<D extends IItem> {

  public void save(OutputStream stream, D item) throws IOException, PersistenceException;
}