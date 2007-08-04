package net.sf.anathema.character.points.view;


import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

public class UpdateActionDelegate implements IViewActionDelegate {

  private IUpdatable view;

  @Override
  public void init(IViewPart viewPart) {
    this.view = (IUpdatable) viewPart;
  }

  @Override
  public void run(IAction action) {
    view.update();
  }

  @Override
  public void selectionChanged(IAction action, ISelection selection) {
    // nothing to do
  }
}