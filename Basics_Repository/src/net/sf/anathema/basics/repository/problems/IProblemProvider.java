package net.sf.anathema.basics.repository.problems;

import java.util.Collection;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.IExecutableExtension;

public interface IProblemProvider extends IExecutableExtension {

  public Collection<IProblem> findProblems(IWorkspaceRoot workspaceRoot);
}