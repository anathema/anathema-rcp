package net.sf.anathema.basics.repository.treecontent.deletion;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public abstract class AbstractPageDelible implements IPageDelible {

  private final ICloseHandler handler;

  public AbstractPageDelible(ICloseHandler handler) {
    this.handler = handler;
  }

  @Override
  public void delete(IWorkbenchPage page) throws CoreException, IOException {
    closeRelatedEditors(page);
    delete();
  }

  protected abstract void delete() throws CoreException, IOException;

  private void closeRelatedEditors(IWorkbenchPage page) throws PartInitException{
    for (IEditorReference reference : page.getEditorReferences()) {
      handler.closeIfRequired(reference);
    }
  }
}