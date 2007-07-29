package net.sf.anathema.basics.repository.treecontent;

import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;

import net.sf.anathema.basics.item.editor.IEditorCloser;
import net.sf.anathema.basics.jface.IFileEditorInput;

public class ResourceCloseHandler {

  private final IEditorCloser closer;
  private final IResourceViewElement element;

  public ResourceCloseHandler(IEditorCloser closer, IResourceViewElement element) {
    this.closer = closer;
    this.element = element;
  }

  public void closeIfRequired(IEditorReference reference) throws PartInitException {
    IEditorInput editorInput = reference.getEditorInput();
    IFileEditorInput input = (IFileEditorInput) editorInput.getAdapter(IFileEditorInput.class);
    IFile file = input.getFile();
    if (element.getEditFile().equals(file)) {
      closer.close(reference.getEditor(false));
    }
  }
}