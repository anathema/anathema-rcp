package net.sf.anathema.basics.repository.treecontent;

import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;

import org.eclipse.core.resources.IResource;

public interface IViewElementProvider {

  public IViewElement getViewElement(IResource resource);
}