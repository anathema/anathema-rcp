package net.sf.anathema.basics.repository.initialization;

import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.basics.repository.itemtype.ItemTypeProvider;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;

public class ProjectInitializer {

  public void initialize() throws Exception {
    for (IItemType itemType : new ItemTypeProvider().getItemTypes()) {
      initProject(itemType.getFolderName());
    }
  }

  private void initProject(String projectName) throws Exception {
    IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
    IProject project = root.getProject(projectName);
    if (project.exists()) {
      return;
    }
    project.create(null);
    project.open(null);
  }
}