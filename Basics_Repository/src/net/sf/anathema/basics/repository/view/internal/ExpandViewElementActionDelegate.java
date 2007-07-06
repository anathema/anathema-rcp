package net.sf.anathema.basics.repository.view.internal;

import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class ExpandViewElementActionDelegate implements IObjectActionDelegate {

  private IViewElement element;
  private IExpandableTree tree;

  @Override
  public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    this.tree = (IExpandableTree) targetPart;
  }

  @Override
  public void run(IAction action) {
    tree.expand(element);
  }

  @Override
  public void selectionChanged(IAction action, ISelection selection) {
    this.element = (IViewElement) ((StructuredSelection) selection).getFirstElement();
  }
}