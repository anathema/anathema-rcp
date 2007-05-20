package net.sf.anathema.campaign.plot.repository;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.repository.RepositoryPlugin;
import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.basics.repository.treecontent.itemtype.IItemTypeViewElementFactory;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;

public class SeriesViewElementFactory implements IItemTypeViewElementFactory {

  private IItemType itemType;

  @Override
  public List<IViewElement> createViewElements(IViewElement parent) {
    List<IViewElement> elements = new ArrayList<IViewElement>();
    for (IFolder resource : getMembers()) {
      elements.add(new SeriesViewElement(resource, parent, itemType.getUntitledName()));
    }
    return elements;
  }

  private List<IFolder> getMembers() {
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
      RepositoryPlugin.log(IStatus.ERROR, "Could not retrieve project members.", e);
      return new ArrayList<IFolder>();
    }
  }

  @Override
  public void setItemType(IItemType itemType) {
    this.itemType = itemType;
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    // nothing to do
  }
}