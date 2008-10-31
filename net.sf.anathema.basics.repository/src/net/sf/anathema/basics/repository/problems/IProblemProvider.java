package net.sf.anathema.basics.repository.problems;

import java.util.Collection;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.IExecutableExtension;

public interface IProblemProvider extends IExecutableExtension {

  /**
   * Calculate problems and return them to the caller. Problems can be generated by a number of means including markers
   * and direct analysis. A container, usually the workspace root, is included for resource-based providers. Registered
   * via net.sf.anathema.basics.repository.problemproviders.
   */
  public Collection<IProblem> findProblems(IContainer workspaceRoot);
}