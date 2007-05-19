package net.sf.anathema.framework.editor;

import java.io.IOException;

import net.sf.anathema.framework.item.IItem;
import net.sf.anathema.framework.item.data.IBasicItemData;
import net.sf.anathema.framework.item.persistence.BasicDataItemPersister;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPersistableElement;

public class NewItemEditorInput implements IItemEditorInput {

  private IItem<IBasicItemData> item;
  private IFile file;

  @Override
  public IItem<IBasicItemData> loadItem(BasicDataItemPersister persister) throws PersistenceException, CoreException {
    item = persister.createNew();
    return item;
  }

  @Override
  public void save(BasicDataItemPersister persister) throws IOException, CoreException {
  }

  @Override
  public boolean exists() {
    return false;
  }

  @Override
  public ImageDescriptor getImageDescriptor() {
    return null;
  }

  @Override
  public String getName() {
    return "NewItemEditorInput"; //$NON-NLS-1$
  }

  @Override
  public IPersistableElement getPersistable() {
    return null;
  }

  @Override
  public String getToolTipText() {
    return item.getPrintName();
  }

  @SuppressWarnings("unchecked")
  @Override
  public Object getAdapter(Class adapter) {
    return null;
  }
}