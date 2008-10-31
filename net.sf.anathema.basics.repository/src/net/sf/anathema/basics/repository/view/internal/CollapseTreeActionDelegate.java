package net.sf.anathema.basics.repository.view.internal;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

public class CollapseTreeActionDelegate implements IViewActionDelegate {

  private ICollapsableTree collapsable;

  @Override
  public void init(IViewPart view) {
    this.collapsable = (ICollapsableTree) view;
  }

  @Override
  public void run(IAction action) {
    collapsable.collapseAll();
  }

  @Override
  public void selectionChanged(IAction action, ISelection selection) {
    // nothing to do
  }
}