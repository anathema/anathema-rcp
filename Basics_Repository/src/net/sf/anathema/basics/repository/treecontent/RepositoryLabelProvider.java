package net.sf.anathema.basics.repository.treecontent;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class RepositoryLabelProvider extends LabelProvider implements ITableLabelProvider {
  public String getColumnText(Object obj, int index) {
    IResource resource = (IResource) obj;
    return resource.getName();
  }

  public Image getColumnImage(Object obj, int index) {
    return getImage(obj);
  }

  @Override
  public Image getImage(Object obj) {
    return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
  }
}