package net.sf.anathema.character.core.resource;

import org.eclipse.core.resources.IResource;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public interface IResourceEditorOpener {

  public void openEditor(IWorkbenchPage page, IResource modelResource) throws PartInitException;
}