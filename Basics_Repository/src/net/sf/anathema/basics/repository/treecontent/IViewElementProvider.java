package net.sf.anathema.basics.repository.treecontent;

import net.sf.anathema.basics.repository.linkage.util.ILink;
import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;

public interface IViewElementProvider {

  public IViewElement getViewElement(ILink linkageId);
}