package net.sf.anathema.basics.repository.problems;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.repository.RepositoryPlugin;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
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
    try {
      addProblemsForContainer((IContainer) inputElement, problems);
    }
    catch (Exception e) {
      RepositoryPlugin.getDefaultInstance().log(IStatus.ERROR, "Unable to display problems for " + inputElement, e);
    }
    return problems.toArray(new IProblem[problems.size()]);
  }

  private void addProblemsForContainer(IContainer container, List<IProblem> problems) throws CoreException {
    for (IResource resource : container.members()) {
      if (resource instanceof IContainer) {
        addProblemsForContainer((IContainer) resource, problems);
      }
      else {
        addProblems(resource, problems);
      }
    }
  }

  private void addProblems(IResource resource, List<IProblem> problems) throws CoreException {
    for (IMarker marker : resource.findMarkers("net.sf.anathema.markers.view.element", true, IResource.DEPTH_ONE)) { //$NON-NLS-1$
      problems.add(new MarkerProblem(marker));
    }
  }
}