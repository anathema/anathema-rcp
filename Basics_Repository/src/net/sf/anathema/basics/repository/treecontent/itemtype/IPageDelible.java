package net.sf.anathema.basics.repository.treecontent.itemtype;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IWorkbenchPage;

public interface IPageDelible {

  public void delete(IWorkbenchPage page) throws CoreException, IOException;
}