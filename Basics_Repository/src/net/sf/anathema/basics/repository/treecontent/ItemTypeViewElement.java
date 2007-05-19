package net.sf.anathema.basics.repository.treecontent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.basics.repository.RepositoryPlugin;
import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.basics.repository.itemtype.IItemType;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

public class ItemTypeViewElement implements IViewElement {

  private final IItemType type;

  public ItemTypeViewElement(IItemType type) {
    this.type = type;
  }

  @Override
  public Object[] getChildren() {
    List<IViewElement> elements = new ArrayList<IViewElement>();
    for (IResource resource : getMembers()) {
      elements.add(new ResourceViewElement(resource, new RegExPrintNameProvider(), this, type.getUntitledName()));
    }
    return elements.toArray(new IViewElement[elements.size()]);
  }

  @Override
  public Object getParent() {
    return null;
  }

  @Override
  public boolean hasChildren() {
    return getMembers().size() != 0;
  }

  private List<IResource> getMembers() {
    IProject project = RepositoryUtilities.getProject(type);
    List<IResource> members = new ArrayList<IResource>();
    try {
      for (IResource resource : project.members()) {
        if (type.getFileExtension().equals(resource.getFileExtension())) {
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

  @Override
  public String getDisplayName() {
    return type.getName();
  }

  @Override
  public Image getImage() {
    URL iconUrl = type.getIconUrl();
    if (iconUrl != null) {
      return ImageDescriptor.createFromURL(iconUrl).createImage();
    }
    return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
  }

  @Override
  public void openEditor(IWorkbenchPage page) {
    // nothing to do
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof ItemTypeViewElement)) {
      return false;
    }
    ItemTypeViewElement other = (ItemTypeViewElement) object;
    return ObjectUtilities.equals(type, other.type);
  }

  @Override
  public int hashCode() {
    return type.hashCode();
  }
}