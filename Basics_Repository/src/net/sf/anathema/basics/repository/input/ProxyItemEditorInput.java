package net.sf.anathema.basics.repository.input;

import java.io.IOException;

import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.basics.item.IItemEditorInput;
import net.sf.anathema.basics.item.data.IBasicItemData;
import net.sf.anathema.basics.item.persistence.BasicDataItemPersister;
import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPersistableElement;

public class ProxyItemEditorInput implements IItemEditorInput {

  private IFileItemEditorInput delegateInput;
  private final IItemType itemType;

  public ProxyItemEditorInput(IItemType itemType) {
    this.itemType = itemType;
    delegateInput = new NewItemEditorInput(itemType);
  }

  @Override
  public IItem<IBasicItemData> loadItem(BasicDataItemPersister persister) throws PersistenceException, CoreException {
    return delegateInput.loadItem(persister);
  }

  @Override
  public IItem<IBasicItemData> save(BasicDataItemPersister persister)
      throws IOException,
      CoreException,
      PersistenceException {
    IItem<IBasicItemData> item = delegateInput.save(persister);
    FileItemEditorInput input = new FileItemEditorInput(
        delegateInput.getFile(),
        itemType.getUntitledName(),
        getImageDescriptor());
    input.setItem(item);
    delegateInput = input;
    return item;
  }

  @Override
  public boolean exists() {
    return delegateInput.exists();
  }

  @Override
  public ImageDescriptor getImageDescriptor() {
    return delegateInput.getImageDescriptor();
  }

  @Override
  public String getName() {
    return delegateInput.getName();
  }

  @Override
  public IPersistableElement getPersistable() {
    return delegateInput.getPersistable();
  }

  @Override
  public String getToolTipText() {
    return delegateInput.getToolTipText();
  }

  @Override
  public Object getAdapter(Class adapter) {
    return delegateInput.getAdapter(adapter);
  }

  @Override
  public boolean equals(Object arg0) {
    return delegateInput.equals(arg0);
  }

  @Override
  public String getDisplayName() {
    return delegateInput.getDisplayName();
  }
}