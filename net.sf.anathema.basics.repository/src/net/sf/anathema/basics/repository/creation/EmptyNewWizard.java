package net.sf.anathema.basics.repository.creation;

import net.sf.anathema.basics.jface.wizards.EmptyWizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;

public abstract class EmptyNewWizard extends EmptyWizard implements INewWizard {

  private IWorkbenchPage workbenchPage;

  protected final IWorkbenchPage getWorkbenchPage() {
    return workbenchPage;
  }

  @Override
  public void init(IWorkbench workbench, IStructuredSelection selection) {
    workbenchPage = workbench.getActiveWorkbenchWindow().getActivePage();
  }
}