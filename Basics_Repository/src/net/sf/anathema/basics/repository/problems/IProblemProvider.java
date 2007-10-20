package net.sf.anathema.basics.repository.problems;

import java.util.Collection;

import org.eclipse.core.resources.IContainer;

public interface IProblemProvider {

  public Collection<IProblem> findProblems(IContainer workspaceRoot);
}