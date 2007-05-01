package net.sf.anathema.basics.repository.treecontent;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.basics.repository.itemtype.ItemTypeProvider;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class TypedTreeContentProvider implements ITreeContentProvider {
  public void inputChanged(Viewer v, Object oldInput, Object newInput) {
    // nothing to do
  }

  public void dispose() {
    // nothing to do
  }

  public Object[] getElements(Object parent) {
    List<IViewElement> list = new ArrayList<IViewElement>();
    for (IItemType type : new ItemTypeProvider().getItemTypes()) {
      list.add(new ItemTypeViewElement(type));
    }
    return list.toArray(new IViewElement[list.size()]);
  }

  public Object[] getChildren(Object parentElement) {
    return cast(parentElement).getChildren();
  }

  public Object getParent(Object element) {
    return cast(element).getParent();
  }

  public boolean hasChildren(Object element) {
    return cast(element).hasChildren();
  }

  private IViewElement cast(Object parentElement) {
    return ((IViewElement) parentElement);
  }
}