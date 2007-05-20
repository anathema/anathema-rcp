package net.sf.anathema.basics.repository.treecontent.itemtype;

import java.util.List;

import net.sf.anathema.basics.repository.itemtype.IItemType;

import org.eclipse.core.runtime.IExecutableExtension;

public interface IItemTypeViewElementFactory extends IExecutableExtension{
  
  public void setItemType(IItemType itemType);

  public List<IViewElement> createViewElements(IViewElement parent);
}