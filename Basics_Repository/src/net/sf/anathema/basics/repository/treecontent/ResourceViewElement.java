package net.sf.anathema.basics.repository.treecontent;

import java.io.IOException;

import net.sf.anathema.basics.repository.treecontent.itemtype.AbstractResourceViewElement;
import net.sf.anathema.basics.repository.treecontent.itemtype.IPrintNameProvider;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class ResourceViewElement extends AbstractResourceViewElement implements IResourceViewElement {
  private final IFile file;

  public ResourceViewElement(
      IFile resource,
      IPrintNameProvider printNameProvider,
      IViewElement parent,
      String untitledName) {
    super(untitledName, parent, printNameProvider);
    this.file = resource;
  }

  @Override
  public IViewElement[] getChildren() {
    return new IViewElement[0];
  }

  @Override
  public boolean hasChildren() {
    return false;
  }

  @Override
  public IFile getEditFile() {
    return file;
  }

  @Override
  protected void delete() throws CoreException, IOException {
    // TODO Monitor
    file.delete(true, new NullProgressMonitor());
  }

  @Override
  protected void closeRelatedEditors(IWorkbenchPage page) throws PartInitException {
    ResourceCloseHandler closeHandler = new ResourceCloseHandler(this);
    for (IEditorReference reference : page.getEditorReferences()) {
      closeHandler.closeIfRequired(reference);
    }
  }
}