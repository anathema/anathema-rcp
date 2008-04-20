package net.sf.anathema.basics.repository.treecontent;

import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;

import net.sf.anathema.basics.item.editor.EditorCloser;
import net.sf.anathema.basics.item.editor.IEditorCloser;
import net.sf.anathema.basics.jface.IFileEditorInput;
import net.sf.anathema.basics.repository.treecontent.deletion.ICloseHandler;

public class ResourceCloseHandler implements ICloseHandler {

  private final IEditorCloser closer;
  private final IFile file;

  public ResourceCloseHandler(IFile file) {
    this(new EditorCloser(), file);
  }

  public ResourceCloseHandler(IEditorCloser closer, IFile file) {
    this.closer = closer;
    this.file = file;
  }

  public void closeIfRequired(IEditorReference reference) throws PartInitException {
    IEditorInput editorInput = reference.getEditorInput();
    IFileEditorInput input = (IFileEditorInput) editorInput.getAdapter(IFileEditorInput.class);
    if (input == null) {
    	return;
    }
    if (file.equals(input.getFile())) {
      closer.close(reference.getEditor(false));
    }
  }
}