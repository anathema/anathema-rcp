package net.sf.anathema.basics.repository.treecontent.itemtype;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public interface IEditorOpener {

  public void openEditor(IWorkbenchPage page) throws PartInitException;
}