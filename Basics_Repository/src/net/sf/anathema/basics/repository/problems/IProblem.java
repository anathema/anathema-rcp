package net.sf.anathema.basics.repository.problems;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IWorkbenchPage;

/** Usually created by an IProblemProvider. */
public interface IProblem {

  /** Return the human-readable description. */
  public String getDescription();

  /** Return the human-readable path of the problem's resource. */
  public String getPath();

  /** Open an editor showing the resource containing the problem. */
  public void showSource(IWorkbenchPage page) throws CoreException;
}