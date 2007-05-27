package net.sf.anathema.basics.repository.treecontent.itemtype;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.repository.RepositoryPlugin;
import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.basics.repository.messages.BasicRepositoryMessages;
import net.sf.anathema.basics.repository.treecontent.ResourceViewElement;

import org.eclipse.core.resources.IFile;
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
    for (IFile file : getMembers()) {
      RegExPrintNameProvider regExPrintNameProvider = new RegExPrintNameProvider();
      elements.add(new ResourceViewElement(file, regExPrintNameProvider, parent, itemType.getUntitledName()));
    }
    return elements;
  }

  private List<IFile> getMembers() {
    IProject project = RepositoryUtilities.getProject(itemType);
    List<IFile> members = new ArrayList<IFile>();
    try {
      for (IResource resource : project.members()) {
        if (resource instanceof IFile && supports(resource)) {
          members.add((IFile) resource);
        }
      }
      return members;
    }
    catch (CoreException e) {
      RepositoryPlugin.log(IStatus.ERROR, BasicRepositoryMessages.RepositoryBasics_ProjectMemberRetrievingErrorMessage, e);
      return new ArrayList<IFile>();
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