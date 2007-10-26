package net.sf.anathema.basics.repository.treecontent.itemtype;

import java.net.URL;
import java.util.List;

import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.repository.RepositoryPlugin;
import net.sf.anathema.basics.repository.itemtype.IItemType;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IWorkbenchPage;

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
      RepositoryPlugin.getDefaultInstance().log(
          IStatus.ERROR,
          NLS.bind(Messages.ItemTypeViewElement_Error_FailedReadingItemsMessage, type.getName()),
          e);
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
  public URL getImageUrl() {
    return type.getIconUrl();
    // TODO beim richtigen Clienten benutzen
    //return PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_ELEMENT);
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

  @SuppressWarnings("unchecked")
  @Override
  public Object getAdapter(Class adapter) {
    return null;
  }
}