package net.sf.anathema.basics.repository.problems;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.jface.viewers.Viewer;

public class ProblemsContentProvider extends AbstractFlatTreeContentProvider {

  @Override
  public void dispose() {
    // nothing to do
  }

  @Override
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    // nothing to do
  }

  @Override
  public IProblem[] getElements(Object inputElement) {
    List<IProblem> problems = new ArrayList<IProblem>();
    problems.addAll(new ResourceProblemProvider().findProblems((IContainer) inputElement));
    return problems.toArray(new IProblem[problems.size()]);
  }
}