package net.sf.anathema.basics.repository.view.internal;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.Viewer;

public class RevealingSelector implements ISelector {

  private final Viewer viewer;

  public RevealingSelector(Viewer viewer) {
    this.viewer = viewer;
  }
  
  @Override
  public void setSelection(ISelection selection) {
    viewer.setSelection(selection, true);
  }
}