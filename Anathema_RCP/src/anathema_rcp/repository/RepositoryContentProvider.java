package anathema_rcp.repository;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class RepositoryContentProvider implements ITreeContentProvider {
  public void inputChanged(Viewer v, Object oldInput, Object newInput) {
    // nothing to do
  }

  public void dispose() {
    // nothing to do
  }

  public Object[] getElements(Object parent) {
    IWorkspace workspace = ResourcesPlugin.getWorkspace();
    IWorkspaceRoot root = workspace.getRoot();
    IProject project = root.getProject("Notes"); //$NON-NLS-1$
    if (!project.exists()) {
      try {
        project.create(null);
        project.open(null);
      }
      catch (CoreException e) {
        e.printStackTrace();
      }
    }
    return root.getProjects();
  }

  public Object[] getChildren(Object parentElement) {
    if (parentElement instanceof IContainer) {
      IContainer container = (IContainer) parentElement;
      try {
        return container.members();
      }
      catch (CoreException e) {
        return new Object[0];
      }
    }
    return new Object[0];
  }

  public Object getParent(Object element) {
    return null;
  }

  public boolean hasChildren(Object element) {
    return element instanceof IContainer;
  }
}