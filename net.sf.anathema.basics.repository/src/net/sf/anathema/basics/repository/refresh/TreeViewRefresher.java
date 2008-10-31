package net.sf.anathema.basics.repository.refresh;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;

public class TreeViewRefresher extends ColumnViewRefresher {

  private final TreeViewer viewer;

  public TreeViewRefresher(TreeViewer viewer, Display display) {
    super(viewer, display);
    this.viewer = viewer;
  }

  @Override
  protected void refresh() {
    Object[] expandedElements = viewer.getExpandedElements();
    super.refresh();
    viewer.setExpandedElements(expandedElements);
  }
}