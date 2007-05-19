package net.sf.anathema.framework.editor;

import java.io.IOException;

import net.sf.anathema.framework.item.IItem;
import net.sf.anathema.framework.item.data.IBasicItemData;
import net.sf.anathema.framework.item.persistence.BasicDataItemPersister;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorInput;

public interface IItemEditorInput extends IEditorInput {

  public void save(BasicDataItemPersister persister) throws IOException, CoreException, PersistenceException;

  public IItem<IBasicItemData> loadItem(BasicDataItemPersister persister) throws PersistenceException, CoreException;
}