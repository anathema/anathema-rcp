package net.sf.anathema.basics.repository.treecontent;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public interface IViewElement {

  public boolean hasChildren();

  public Object[] getChildren();

  public Object getParent();

  public String getDisplayName();

  public Image getImage();

  public void openEditor(IWorkbenchPage page) throws PartInitException;
}