package net.sf.anathema.basics.eclipse.ui;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public interface IEditorOpener {

  public void openEditor(IWorkbenchPage page) throws PartInitException;
}