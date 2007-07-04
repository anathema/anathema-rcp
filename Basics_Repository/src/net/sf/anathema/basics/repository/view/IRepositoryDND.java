package net.sf.anathema.basics.repository.view;

import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.IViewSite;

public interface IRepositoryDND extends IExecutableExtension {

  public void initDragAndDrop(TreeViewer viewer, IViewSite site);
}