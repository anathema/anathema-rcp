package net.sf.anathema.basics.repository.input;

import java.io.IOException;

import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.basics.item.IItemEditorInput;
import net.sf.anathema.basics.item.data.IBasicItemData;
import net.sf.anathema.basics.item.persistence.BasicDataItemPersister;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPersistableElement;

public class ProxyItemEditorInput implements IItemEditorInput {

  private IFileItemEditorInput delegateInput;
  private final String untitledName;

  public ProxyItemEditorInput(String untitledName, IFileItemEditorInput initalDelegate) {
    this.untitledName = untitledName;
    this.delegateInput = initalDelegate;
  }

  @Override
  public IItem<IBasicItemData> loadItem(BasicDataItemPersister persister) throws PersistenceException, CoreException {
    return delegateInput.loadItem(persister);
  }

  @Override
  public IItem<IBasicItemData> save(BasicDataItemPersister persister, IProgressMonitor monitor)
      throws IOException,
      CoreException,
      PersistenceException {
    IItem<IBasicItemData> item = delegateInput.save(persister, monitor);
    FileItemEditorInput input = new FileItemEditorInput(
        delegateInput.getFile(),
        untitledName,
        delegateInput.getImageDescriptor());
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

  @SuppressWarnings("unchecked")
  @Override
  public Object getAdapter(Class adapter) {
    return delegateInput.getAdapter(adapter);
  }

  @Override
  public boolean equals(Object arg0) {
    return delegateInput.equals(arg0);
  }
}