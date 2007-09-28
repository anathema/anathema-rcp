package net.sf.anathema.basics.repository.treecontent.itemtype;

import net.sf.anathema.basics.eclipse.ui.IEditorOpener;
import net.sf.anathema.basics.jface.resource.IImagedAdaptable;

public interface IViewElement extends IImagedAdaptable, IDisplayNameProvider, IEditorOpener {

  public boolean hasChildren();

  public IViewElement[] getChildren();

  public IViewElement getParent();
}