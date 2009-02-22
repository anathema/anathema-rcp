package net.sf.anathema.basics.repository.problems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.basics.repository.RepositoryPlugin;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;

public class ResourceProblemProvider extends UnconfiguredExecutableExtension implements IProblemProvider {

  @Override
  public Collection<IProblem> findProblems(IContainer workspaceRoot) {
    List<IProblem> problems = new ArrayList<IProblem>();
    try {
      addProblemsForContainer(workspaceRoot, problems);
    }
    catch (Exception e) {
      RepositoryPlugin.getDefaultInstance().log(IStatus.ERROR, Messages.ResourceProblemProvider_ErrorMessage, e);
    }
    return problems;
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