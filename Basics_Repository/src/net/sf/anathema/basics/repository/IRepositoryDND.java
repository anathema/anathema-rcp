package net.sf.anathema.basics.repository;

import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.swt.widgets.Control;

public interface IRepositoryDND extends IExecutableExtension {

  public void initDragAndDrop(Control control);
}