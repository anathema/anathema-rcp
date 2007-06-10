package net.sf.anathema.basics.repository.treecontent.itemtype;

import java.net.URL;
import java.util.List;

import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.repository.itemtype.IItemType;

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
  public IViewElement[] getChildren() {
    try {
      IItemTypeViewElementFactory elementFactory = type.getViewElementFactory();
      List<IViewElement> elements = elementFactory.createViewElements(this);
      return elements.toArray(new IViewElement[elements.size()]);
    }
    catch (ExtensionException e) {
      // TODO: Fehlerhandling
      e.printStackTrace();
      return new IViewElement[0];
    }
  }

  @Override
  public IViewElement getParent() {
    return null;
  }

  @Override
  public boolean hasChildren() {
    return getChildren().length != 0;
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

  @Override
  public Object getAdapter(Class adapter) {
    return null;
  }
}