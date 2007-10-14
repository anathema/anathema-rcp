package net.sf.anathema.basics.repository.problems;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ProblemsContentProvider implements ITreeContentProvider {

  @Override
  public void dispose() {
    // TODO Auto-generated method stub
  }

  @Override
  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    // TODO Auto-generated method stub
  }

  @Override
  public Object[] getChildren(Object parentElement) {
    return new Object[0];
  }

  @Override
  public Object getParent(Object element) {
    return null;
  }

  @Override
  public boolean hasChildren(Object element) {
    return false;
  }

  @Override
  public Object[] getElements(Object inputElement) {
    List<IMarker> markers = new ArrayList<IMarker>();
    try {
      addProblemsForContainer(ResourcesPlugin.getWorkspace().getRoot(), markers);

    }
    catch (Exception e) {
      // TODO Exception Handling
    }
    return markers.toArray(new IMarker[markers.size()]);
  }

  private void addProblemsForContainer(IContainer container, List<IMarker> markers) throws CoreException {
    for (IResource resource : container.members()) {
      if (resource instanceof IContainer) {
        addProblemsForContainer((IContainer) resource, markers);
      }
      else {
        addProblems(resource, markers);
      }
    }
  }

  private void addProblems(IResource resource, List<IMarker> markers) throws CoreException {
    for (IMarker marker : resource.findMarkers("net.sf.anathema.markers.view.element", true, 1)) { //$NON-NLS-1$
      markers.add(marker);
    }
  }
}