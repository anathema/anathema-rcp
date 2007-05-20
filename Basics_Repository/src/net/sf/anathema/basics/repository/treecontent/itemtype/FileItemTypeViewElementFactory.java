package net.sf.anathema.basics.repository.treecontent.itemtype;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.repository.RepositoryPlugin;
import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.basics.repository.treecontent.RegExPrintNameProvider;
import net.sf.anathema.basics.repository.treecontent.ResourceViewElement;
import net.sf.anathema.basics.repository.treecontent.viewelement.IViewElement;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;

public class FileItemTypeViewElementFactory implements IItemTypeViewElementFactory {

  private IItemType itemType;

  @Override
  public List<IViewElement> createViewElements(IViewElement parent) {
    List<IViewElement> elements = new ArrayList<IViewElement>();
    for (IResource resource : getMembers()) {
      RegExPrintNameProvider regExPrintNameProvider = new RegExPrintNameProvider();
      elements.add(new ResourceViewElement(resource, regExPrintNameProvider, parent, itemType.getUntitledName()));
    }
    return elements;
  }

  private List<IResource> getMembers() {
    IProject project = RepositoryUtilities.getProject(itemType);
    List<IResource> members = new ArrayList<IResource>();
    try {
      for (IResource resource : project.members()) {
        if (supports(resource)) {
          members.add(resource);
        }
      }
      return members;
    }
    catch (CoreException e) {
      RepositoryPlugin.log(IStatus.ERROR, "Could not retrieve project members.", e);
      return new ArrayList<IResource>();
    }
  }

  private boolean supports(IResource resource) {
    return itemType.getFileExtension().equals(resource.getFileExtension());
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