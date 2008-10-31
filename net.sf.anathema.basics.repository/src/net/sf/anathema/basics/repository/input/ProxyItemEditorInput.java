package net.sf.anathema.basics.repository.input;

import java.io.IOException;

import net.sf.anathema.basics.item.IPersistableEditorInput;
import net.sf.anathema.basics.item.text.ITitledText;
import net.sf.anathema.basics.repository.input.internal.FileItemEditorInput;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPersistableElement;

public class ProxyItemEditorInput implements IPersistableEditorInput<ITitledText> {

  private IFileItemEditorInput<ITitledText> delegateInput;
  private final String untitledName;

  public ProxyItemEditorInput(String untitledName, IFileItemEditorInput<ITitledText> initalDelegate) {
    this.untitledName = untitledName;
    this.delegateInput = initalDelegate;
  }

  @Override
  public ITitledText getItem() {
    return delegateInput.getItem();
  }

  @Override
  public ITitledText save(IProgressMonitor monitor) throws IOException, CoreException, PersistenceException {
    ITitledText itemData = delegateInput.save(monitor);
    FileItemEditorInput input = new FileItemEditorInput(
        delegateInput.getFile(),
        untitledName,
        delegateInput.getImageUrl());
    input.setItem(itemData);
    delegateInput = input;
    return itemData;
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