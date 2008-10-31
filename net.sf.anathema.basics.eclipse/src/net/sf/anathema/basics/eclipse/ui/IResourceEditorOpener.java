package net.sf.anathema.basics.eclipse.ui;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public interface IResourceEditorOpener extends IExecutableExtension {

  /** Opens the given resource in the page provided. */
  public void openEditor(IWorkbenchPage page, IResource resource) throws PartInitException;
}