package net.sf.anathema.basics.repository.treecontent.itemtype;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public interface IViewElement extends IAdaptable, IDisplayNameProvider {

  public boolean hasChildren();

  public IViewElement[] getChildren();

  public IViewElement getParent();

  public ImageDescriptor getImageDescriptor();

  public void openEditor(IWorkbenchPage page) throws PartInitException;

  public void delete(IWorkbenchPage page) throws CoreException, IOException;

  public boolean canBeDeleted();
}