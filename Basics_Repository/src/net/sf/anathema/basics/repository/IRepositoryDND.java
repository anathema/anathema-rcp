package net.sf.anathema.basics.repository;

import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.viewers.TreeViewer;

public interface IRepositoryDND extends IExecutableExtension {

  public void initDragAndDrop(TreeViewer viewer);
}