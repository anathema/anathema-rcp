package net.sf.anathema.basics.repository.treecontent;

import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;

import net.sf.anathema.basics.item.editor.IEditorCloser;
import net.sf.anathema.basics.jface.IFileEditorInput;

public class ResourceCloseHandler {

  private final IEditorCloser pageEditorCloser;
  private final IResourceViewElement resourceViewElement;

  public ResourceCloseHandler(IEditorCloser pageEditorCloser, IResourceViewElement resourceViewElement) {
    this.pageEditorCloser = pageEditorCloser;
    this.resourceViewElement = resourceViewElement;
  }

  public void closeIfRequired(IEditorReference reference) throws PartInitException {
    IEditorInput editorInput = reference.getEditorInput();
    IFileEditorInput input = (IFileEditorInput) editorInput.getAdapter(IFileEditorInput.class);
    IFile file = input.getFile();
    if (resourceViewElement.getEditFile().equals(file)) {
      pageEditorCloser.close(reference.getEditor(false));
    }
  }
}