package net.sf.anathema.basics.repository.treecontent;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import net.sf.anathema.basics.repository.itemtype.IItemType;

public class ItemTypeViewElement implements IViewElement {

  private final IItemType type;

  public ItemTypeViewElement(IItemType type) {
    this.type = type;
  }

  @Override
  public Object[] getChildren() {
    return new Object[0];
  }

  @Override
  public Object getParent() {
    return null;
  }

  @Override
  public boolean hasChildren() {
    return false;
  }

  @Override
  public String getDisplayName() {
    return type.getName();
  }

  @Override
  public Image getImage() {
    return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
  }
}