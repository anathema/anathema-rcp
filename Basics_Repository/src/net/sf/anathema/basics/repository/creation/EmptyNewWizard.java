package net.sf.anathema.basics.repository.creation;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;

public abstract class EmptyNewWizard implements INewWizard {

  private IWorkbenchPage workbenchPage;
  
  protected final IWorkbenchPage getWorkbenchPage() {
    return workbenchPage;
  }

  @Override
  public void init(IWorkbench workbench, IStructuredSelection selection) {
    workbenchPage = workbench.getActiveWorkbenchWindow().getActivePage();
  }

  @Override
  public void addPages() {
    // nothing to do
  }

  @Override
  public boolean canFinish() {
    return true;
  }

  @Override
  public void createPageControls(Composite pageContainer) {
    // nothing to do
  }

  @Override
  public void dispose() {
    // nothing to do
  }

  @Override
  public IWizardContainer getContainer() {
    return null;
  }

  @Override
  public Image getDefaultPageImage() {
    return null;
  }

  @Override
  public IDialogSettings getDialogSettings() {
    return null;
  }

  @Override
  public IWizardPage getNextPage(IWizardPage page) {
    return null;
  }

  @Override
  public IWizardPage getPage(String pageName) {
    return null;
  }

  @Override
  public int getPageCount() {
    return 0;
  }

  @Override
  public IWizardPage[] getPages() {
    return null;
  }

  @Override
  public IWizardPage getPreviousPage(IWizardPage page) {
    return null;
  }

  @Override
  public IWizardPage getStartingPage() {
    return null;
  }

  @Override
  public RGB getTitleBarColor() {
    return null;
  }

  @Override
  public String getWindowTitle() {
    return null;
  }

  @Override
  public boolean isHelpAvailable() {
    return false;
  }

  @Override
  public boolean needsPreviousAndNextButtons() {
    return false;
  }

  @Override
  public boolean needsProgressMonitor() {
    return false;
  }

  @Override
  public boolean performCancel() {
    return true;
  }

  @Override
  public void setContainer(IWizardContainer wizardContainer) {
    // nothing to do
  }

}