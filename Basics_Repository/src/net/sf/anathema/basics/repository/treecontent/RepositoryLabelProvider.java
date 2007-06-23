package net.sf.anathema.basics.repository.treecontent;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class RepositoryLabelProvider extends LabelProvider implements ITableLabelProvider {

  private Map<Object, Image> images = new HashMap<Object, Image>();

  @Override
  public String getText(Object element) {
    return cast(element).getDisplayName();
  }

  @Override
  public String getColumnText(Object obj, int index) {
    return getText(obj);
  }

  @Override
  public Image getColumnImage(Object obj, int index) {
    return getImage(obj);
  }

  @Override
  public Image getImage(Object obj) {
    if (images.get(obj) != null) {
      return images.get(obj);
    }
    Image image = cast(obj).getImageDescriptor().createImage();
    images.put(obj, image);
    return image;
  }

  private IViewElement cast(Object element) {
    return ((IViewElement) element);
  }

  @Override
  public void dispose() {
    super.dispose();
    for (Image image : images.values()) {
      image.dispose();
    }
    images.clear();
  }
}