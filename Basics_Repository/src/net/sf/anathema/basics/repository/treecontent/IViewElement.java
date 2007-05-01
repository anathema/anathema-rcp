package net.sf.anathema.basics.repository.treecontent;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPage;

public interface IViewElement {

  boolean hasChildren();

  Object[] getChildren();

  Object getParent();

  String getDisplayName();

  Image getImage();

  void openEditor(IWorkbenchPage page);
}