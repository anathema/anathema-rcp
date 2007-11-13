package net.sf.anathema.basics.importwizard;

import java.io.File;
import java.io.FileNotFoundException;

import net.disy.commons.core.model.BooleanModel;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.basics.importwizard.plugin.ImportWizardPluginConstants;
import net.sf.anathema.basics.repository.itemtype.IItemType;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.progress.UIJob;

public abstract class AbstractImportWizard extends Wizard implements IImportWizard {

  private final class ImportJob extends UIJob {
    private IFile internalFile;

    public ImportJob() {
      super(Messages.AbstractImportWizard_ImportJobName);
    }

    @Override
    public IStatus runInUIThread(IProgressMonitor monitor) {
      try {
        internalFile = createInternalFile(fileModel.getFile().getName());
        IStatus status = importFile(monitor);
        if (!status.isOK()) {
          undoImport(internalFile, monitor);
          MessageDialog.openError(getShell(), Messages.AbstractImportWizard_FailureMessageTitle, status.getMessage());
          return status;
        }
        return new Status(
            IStatus.OK,
            ImportWizardPluginConstants.PLUGIN_ID,
            Messages.AbstractImportWizard_ImportSuccessful);
      }
      catch (CoreException e) {
        String message = NLS.bind(Messages.AbstractImportWizard_ImportError, getItemType().getName());
        logger.error(message, e);
        return logger.createErrorStatus(message, e);
      }
    }

    private IStatus importFile(IProgressMonitor monitor) throws CoreException {
      try {
        IStatus importStatus = runImport(fileModel.getFile(), internalFile, monitor);
        return importStatus;
      }
      catch (Exception e) {
        try {
          undoImport(internalFile, monitor);
        }
        catch (CoreException c) {
          logger.error(Messages.AbstractImportWizard_ErrorUndoing, c);
        }
        throw new CoreException(logger.createErrorStatus(Messages.AbstractImportWizard_ImportFailed, e));
      }
    }
  }

  private final Logger logger = new Logger(ImportWizardPluginConstants.PLUGIN_ID);
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
    final ImportJob job = new ImportJob();
    job.setRule((ResourcesPlugin.getWorkspace().getRoot()));
    job.schedule();
    final Display display = Display.getCurrent();
    job.addJobChangeListener(new JobChangeAdapter() {
      @Override
      public void done(IJobChangeEvent event) {
        if (event.getResult().getSeverity() == IStatus.OK) {
          showInEditor(display, job.internalFile);
        }
      }
    });
    return true;
  }

  private void showInEditor(final Display display, final IFile file) {
    if (openModel.getValue()) {
      display.asyncExec(new Runnable() {
        public void run() {
          IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
          try {
            openEditor(file, page);
          }
          catch (PartInitException e) {
            logger.error(NLS.bind(Messages.AbstractImportWizard_CouldNotOpen, file.getName()), e);
          }
        }

      });
    }
  }

  protected abstract void openEditor(final IFile file, IWorkbenchPage page) throws PartInitException;

  protected abstract IFile createInternalFile(String filename) throws CoreException;

  protected abstract IStatus runImport(File externalFile, IFile internalFile, IProgressMonitor monitor)
      throws CoreException,
      FileNotFoundException;

  protected abstract void undoImport(IFile internalFile, IProgressMonitor monitor) throws CoreException;

  protected abstract IItemType getItemType();

  @Override
  public void init(IWorkbench currentWorkbench, IStructuredSelection selection) {
    this.workbench = currentWorkbench;
    setWindowTitle(NLS.bind(Messages.AbstractImportWizard_WindowTitle, getItemType().getName()));
    addPage(createImportPage(fileModel, openModel));
  }

  protected abstract IWizardPage createImportPage(IFileSelectionModel filemodel, BooleanModel openmodel);
}