package net.sf.anathema.character.points;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

public class ForceUpdateActionDelegate implements IViewActionDelegate {

  private IUpdatableView view;

  @Override
  public void init(IViewPart viewPart) {
    this.view = (IUpdatableView) viewPart;
  }

  @Override
  public void run(IAction action) {
    view.forceUpdate();
  }

  @Override
  public void selectionChanged(IAction action, ISelection selection) {
    // nothing to do
  }
}