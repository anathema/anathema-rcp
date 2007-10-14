package net.sf.anathema.basics.repository.problems;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IWorkbenchPage;

public interface IProblem {

  public String getDescription();

  public String getPath();
  
  public void showSource(IWorkbenchPage page) throws CoreException;
}