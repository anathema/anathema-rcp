package net.sf.anathema.basics.repository.treecontent;

import java.io.IOException;
import java.net.URL;

import net.sf.anathema.basics.eclipse.ui.IEditorInputProvider;
import net.sf.anathema.basics.jface.IFileEditorInput;
import net.sf.anathema.basics.repository.treecontent.deletion.IPageDelible;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class DummyResourceViewElement implements IResourceViewElement, IPageDelible, IEditorInputProvider {

  private IFile editorFile;
  private boolean deleted;
  private IFileEditorInput input;

  public void setFile(IFile editorFile) {
    this.editorFile = editorFile;
  }

  @Override
  public IFile getEditFile() {
    return editorFile;
  }

  @Override
  public void delete(IWorkbenchPage page, IProgressMonitor monitor) throws CoreException, IOException {
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
  public URL getImageUrl() {
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
    return this;
  }

  @Override
  public String getDisplayName() {
    throw new UnsupportedOperationException("Dummy"); //$NON-NLS-1$
  }

  public void setInput(IFileEditorInput input) {
    this.input = input;
  }

  @Override
  public IEditorInput getEditorInput() {
    return input;
  }
}