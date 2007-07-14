package net.sf.anathema.basics.repository.linkage;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

public final class RepositoryEditorLinkActionDelegate implements IViewActionDelegate {
  private IViewEditorLinker linker;

  @Override
  public void init(IViewPart view) {
    this.linker = (IViewEditorLinker) view;
  }

  @Override
  public void run(IAction action) {
    linker.setLinkEnabled(action.isChecked());
  }

  @Override
  public void selectionChanged(IAction action, ISelection selection) {
    // nothing to do
  }
}