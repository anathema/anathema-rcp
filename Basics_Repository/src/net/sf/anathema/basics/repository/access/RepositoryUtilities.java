package net.sf.anathema.basics.repository.access;

import net.sf.anathema.basics.repository.itemtype.IItemType;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;

public class RepositoryUtilities {

  public static IProject getProject(IItemType itemType) {
    String projectName = itemType.getProjectName();
    IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
    return root.getProject(projectName);
  }
}