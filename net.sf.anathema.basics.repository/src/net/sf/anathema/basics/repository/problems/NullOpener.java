package net.sf.anathema.basics.repository.problems;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.basics.eclipse.ui.IResourceEditorOpener;

import org.eclipse.core.resources.IResource;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class NullOpener extends UnconfiguredExecutableExtension implements IResourceEditorOpener {

  @Override
  public void openEditor(IWorkbenchPage page, IResource resource) throws PartInitException {
    //nothing to do
  }
}