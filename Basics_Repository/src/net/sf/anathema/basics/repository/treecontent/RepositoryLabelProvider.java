package net.sf.anathema.basics.repository.treecontent;

import net.sf.anathema.basics.repository.treecontent.viewelement.IViewElement;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class RepositoryLabelProvider extends LabelProvider implements ITableLabelProvider {

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
    return cast(obj).getImage();
  }

  private IViewElement cast(Object element) {
    return ((IViewElement) element);
  }
}