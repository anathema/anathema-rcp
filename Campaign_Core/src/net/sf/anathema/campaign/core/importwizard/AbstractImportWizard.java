package net.sf.anathema.campaign.core.importwizard;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

import net.disy.commons.core.model.BooleanModel;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.basics.repository.treecontent.itemtype.ResourceEditorOpener;
import net.sf.anathema.campaign.core.plugin.CampaignCorePluginConstants;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public abstract class AbstractImportWizard extends Wizard implements IImportWizard {

  private final Logger logger = new Logger(CampaignCorePluginConstants.PLUGIN_ID);
  private final BooleanModel openModel = new BooleanModel();
  private final IFileSelectionModel fileModel;
  private IWorkbench workbench;

  public AbstractImportWizard(IFileSelectionStatusFactory factory) {
    this.fileModel = new FileSelectionModel(factory);
  }

  @Override
  public boolean canFinish() {
    return fileModel.isComplete();
  }

  @Override
  public boolean performFinish() {
    try {
      final IFile internalFile = createInternalFile(fileModel.getFile().getName());
      importFile(internalFile);
      showInEditor(internalFile);
      return true;
    }
    catch (Exception e) {
      logger.error(NLS.bind(Messages.AbstractImportWizard_ImportError, getItemType().getName()), e);
      return false;
    }
  }

  protected abstract IFile createInternalFile(String filename) throws CoreException;

  private void importFile(final IFile internalFile) throws InvocationTargetException, InterruptedException {
    workbench.getActiveWorkbenchWindow().run(true, false, new IRunnableWithProgress() {
      @Override
      public void run(IProgressMonitor monitor) throws InvocationTargetException {
        try {
          runImport(fileModel.getFile(), internalFile, monitor);
        }
        catch (Exception e) {
          logger.error("The import failed.", e);
          try {
            undoImport(internalFile, monitor);
          }
          catch (CoreException c) {
            logger.error("An exception occured while undoing the failed import.", c);
          }
          throw new InvocationTargetException(e);
        }
      }
    });
  }

  protected abstract void runImport(File externalFile, IFile internalFile, IProgressMonitor monitor)
      throws CoreException,
      FileNotFoundException;

  protected abstract void undoImport(IFile internalFile, IProgressMonitor monitor)
      throws CoreException;

  private void showInEditor(final IFile file) throws PartInitException {
    if (openModel.getValue()) {
      IItemType itemType = getItemType();
      ImageDescriptor icon = ImageDescriptor.createFromURL(itemType.getIconUrl());
      IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
      new ResourceEditorOpener(file, itemType.getUntitledName(), icon).openEditor(page);
    }
  }

  protected abstract IItemType getItemType();

  @Override
  public void init(IWorkbench currentWorkbench, IStructuredSelection selection) {
    this.workbench = currentWorkbench;
    setWindowTitle(NLS.bind(Messages.AbstractImportWizard_WindowTitle, getItemType().getName()));
    addPage(createImportPage(fileModel, openModel));
  }

  protected abstract IWizardPage createImportPage(IFileSelectionModel filemodel, BooleanModel openmodel);
}