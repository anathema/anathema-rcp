package net.sf.anathema.charms.view;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;

public class ViewerSelectable implements ISelectable {

  private final Viewer viewer;

  public ViewerSelectable(Viewer viewer) {
    this.viewer = viewer;
  }

  @Override
  public void clearSelection() {
    viewer.setSelection(new StructuredSelection());
  }
}