package net.sf.anathema.basics.repository.treecontent;

import java.io.IOException;

import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class DummyResourceViewElement implements IResourceViewElement {

  private IFile editorFile;
  private boolean deleted;

  public void setFile(IFile editorFile) {
    this.editorFile = editorFile;
  }

  @Override
  public IFile getEditFile() {
    return editorFile;
  }

  @Override
  public boolean canBeDeleted() {
    return true;
  }

  @Override
  public void delete(IWorkbenchPage page) throws CoreException, IOException {
    this.deleted = true;
  }

  public boolean isDeleted() {
    return deleted;
  }

  @Override
  public IViewElement[] getChildren() {
    throw new UnsupportedOperationException("Dummy"); //$NON-NLS-1$
  }

  @Override
  public ImageDescriptor getImageDescriptor() {
    throw new UnsupportedOperationException("Dummy"); //$NON-NLS-1$
  }

  @Override
  public IViewElement getParent() {
    throw new UnsupportedOperationException("Dummy"); //$NON-NLS-1$
  }

  @Override
  public boolean hasChildren() {
    throw new UnsupportedOperationException("Dummy"); //$NON-NLS-1$
  }

  @Override
  public void openEditor(IWorkbenchPage page) throws PartInitException {
    throw new UnsupportedOperationException("Dummy"); //$NON-NLS-1$
  }

  @SuppressWarnings("unchecked")
  @Override
  public Object getAdapter(Class adapter) {
    throw new UnsupportedOperationException("Dummy"); //$NON-NLS-1$
  }

  @Override
  public String getDisplayName() {
    throw new UnsupportedOperationException("Dummy"); //$NON-NLS-1$
  }
}