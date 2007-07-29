package net.sf.anathema.basics.repository.treecontent.deletion;

import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;

public interface ICloseHandler {

  public void closeIfRequired(IEditorReference reference) throws PartInitException;
}