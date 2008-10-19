package net.sf.anathema.basics.repository.treecontent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.basics.repository.itemtype.ItemTypeProvider;
import net.sf.anathema.basics.repository.linkage.util.ILink;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.basics.repository.treecontent.itemtype.ItemTypeViewElement;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class TypedTreeContentProvider implements ITreeContentProvider, IViewElementProvider {

  private List<IViewElement> elementList;

  public void inputChanged(Viewer v, Object oldInput, Object newInput) {
    // nothing to do
  }

  public void dispose() {
    // nothing to do
  }

  public Object[] getElements(Object parent) {
    if (elementList == null) {
      elementList = createItemTypeElements();
    }
    return elementList.toArray(new IViewElement[elementList.size()]);
  }

  private List<IViewElement> createItemTypeElements() {
    List<IViewElement> itemTypeElements = new ArrayList<IViewElement>();
    for (IItemType type : new ItemTypeProvider().getItemTypes()) {
      itemTypeElements.add(new ItemTypeViewElement(type));
    }
    return itemTypeElements;
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

  @Override
  public IViewElement getViewElement(ILink linkageId) {
    if (linkageId == null) {
      return null;
    }
    return getViewElement(linkageId, elementList);
  }

  private IViewElement getViewElement(ILink linkageId, IViewElement element) {
    ILink elementResource = (ILink) element.getAdapter(ILink.class);
    if (linkageId.equals(elementResource)) {
      return element;
    }
    return getViewElement(linkageId, Arrays.asList(element.getChildren()));
  }

  private IViewElement getViewElement(ILink linkageId, Iterable<IViewElement> elements) {
    for (IViewElement element : elements) {
      IViewElement foundElement = getViewElement(linkageId, element);
      if (foundElement != null) {
        return foundElement;
      }
    }
    return null;
  }
}