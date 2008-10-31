package net.sf.anathema.basics.repository.initialization;

import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.basics.repository.itemtype.ItemTypeProvider;

import org.eclipse.core.resources.IProject;

public class ProjectInitializer {

  public void initialize() throws Exception {
    for (IItemType itemType : new ItemTypeProvider().getItemTypes()) {
      initProject(itemType);
    }
  }

  private void initProject(IItemType itemType) throws Exception {
    IProject project = RepositoryUtilities.getProject(itemType);
    if (!project.exists()) {
      project.create(null);
    }
    if (!project.isOpen()) {
      project.open(null);
    }
  }
}