package net.sf.anathema.basics.repository.treecontent.itemtype;

import java.util.List;

import net.sf.anathema.basics.repository.itemtype.IItemType;

import org.eclipse.core.runtime.IExecutableExtension;

public interface IItemTypeViewElementFactory extends IExecutableExtension {

  /** Sets the type of item supported by this factory. This should only be called by the ItemType itself. */
  public void setItemType(IItemType itemType);

  /** Creates the top-level elements for the items of the given item type. */
  public List<IViewElement> createViewElements(IViewElement parent);
}