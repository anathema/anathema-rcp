package net.sf.anathema.basics.repository.treecontent.itemtype;

import net.sf.anathema.basics.jface.resource.IImagedAdaptable;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public interface IViewElement extends IImagedAdaptable, IDisplayNameProvider {

  public boolean hasChildren();

  public IViewElement[] getChildren();

  public IViewElement getParent();

  public void openEditor(IWorkbenchPage page) throws PartInitException;
}