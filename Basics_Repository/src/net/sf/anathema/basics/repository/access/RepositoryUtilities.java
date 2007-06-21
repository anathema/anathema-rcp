package net.sf.anathema.basics.repository.access;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.repository.RepositoryPlugin;
import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.basics.repository.messages.BasicRepositoryMessages;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;

public class RepositoryUtilities {

  public static IProject getProject(IItemType itemType) {
    String projectName = itemType.getProjectName();
    IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
    return root.getProject(projectName);
  }

  public static List<IFolder> getItemFolders(IItemType itemType) {
    IProject project = getProject(itemType);
    List<IFolder> members = new ArrayList<IFolder>();
    try {
      for (IResource resource : project.members()) {
        if (resource instanceof IFolder) {
          members.add((IFolder) resource);
        }
      }
    }
    catch (CoreException e) {
      String message = BasicRepositoryMessages.RepositoryBasics_ProjectMemberRetrievingErrorMessage;
      RepositoryPlugin.getDefaultInstance().log(IStatus.ERROR, message, e);
      members.clear();
    }
    return members;
  }
}