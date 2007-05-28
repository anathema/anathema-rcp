package net.sf.anathema.basics.repository.treecontent.itemtype;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.repository.RepositoryPlugin;
import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.basics.repository.messages.BasicRepositoryMessages;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;

public abstract class AbstractFolderBasedViewElementFactory implements IItemTypeViewElementFactory {

  private IItemType itemType;

  protected final List<IFolder> getMembers() {
    IProject project = RepositoryUtilities.getProject(itemType);
    List<IFolder> members = new ArrayList<IFolder>();
    try {
      for (IResource resource : project.members()) {
        if (resource instanceof IFolder) {
          members.add((IFolder) resource);
        }
      }
      return members;
    }
    catch (CoreException e) {
      RepositoryPlugin.log(IStatus.ERROR, BasicRepositoryMessages.RepositoryBasics_ProjectMemberRetrievingErrorMessage, e);
      return new ArrayList<IFolder>();
    }
  }
  
  protected final IItemType getItemType() {
    return itemType;
  }

  @Override
  public final void setItemType(IItemType itemType) {
    this.itemType = itemType;
  }

  @Override
  public final void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    // nothing to do
  }
}