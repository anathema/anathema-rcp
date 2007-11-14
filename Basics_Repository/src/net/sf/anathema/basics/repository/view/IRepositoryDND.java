package net.sf.anathema.basics.repository.view;

import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.IViewSite;

public interface IRepositoryDND extends IExecutableExtension {

  /**
   * Sets up drag sources, drag support and allowed operations for Drag and Drop. Used by the repository tree view.
   * Registered via net.sf.anathema.basics.repository.dnd.
   */
  public void initDragAndDrop(TreeViewer viewer, IViewSite site);
}