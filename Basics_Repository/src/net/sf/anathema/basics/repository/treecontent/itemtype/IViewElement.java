package net.sf.anathema.basics.repository.treecontent.itemtype;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public interface IViewElement extends IAdaptable {

  public boolean hasChildren();

  public IViewElement[] getChildren();

  public IViewElement getParent();

  public String getDisplayName();

  public Image getImage();

  public void openEditor(IWorkbenchPage page) throws PartInitException;
}