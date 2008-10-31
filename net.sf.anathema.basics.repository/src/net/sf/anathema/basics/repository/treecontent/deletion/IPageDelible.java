package net.sf.anathema.basics.repository.treecontent.deletion;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IWorkbenchPage;

public interface IPageDelible {

  public void delete(IWorkbenchPage page, IProgressMonitor monitor) throws CoreException, IOException;
}