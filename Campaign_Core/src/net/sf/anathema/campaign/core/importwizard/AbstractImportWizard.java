package net.sf.anathema.campaign.core.importwizard;

import java.io.File;
import java.io.FileNotFoundException;

import net.disy.commons.core.model.BooleanModel;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.basics.repository.treecontent.itemtype.ResourceEditorOpener;
import net.sf.anathema.campaign.core.plugin.CampaignCorePluginConstants;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
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

  public final class ImportJob extends Job {
    private IFile internalFile;

    public ImportJob(String name) {
      super(name);
    }

    @Override
    protected IStatus run(IProgressMonitor monitor) {
      try {
        internalFile = createInternalFile(fileModel.getFile().getName());
        importFile(monitor);
        return new Status(IStatus.OK, CampaignCorePluginConstants.PLUGIN_ID, "Finished importing.");
      }
      catch (CoreException e) {
        String message = NLS.bind(Messages.AbstractImportWizard_ImportError, getItemType().getName());
        logger.error(message, e);
        return logger.createErrorStatus(message, e);
      }
    }

    private void importFile(IProgressMonitor monitor) throws CoreException {
      try {
        runImport(fileModel.getFile(), internalFile, monitor);
      }
      catch (Exception e) {
        try {
          undoImport(internalFile, monitor);
        }
        catch (CoreException c) {
          logger.error("An exception occured while undoing the failed import.", c);
        }
        throw new CoreException(logger.createErrorStatus("The import failed.", e));
      }
    }
  }

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
    final ImportJob job = new ImportJob("Import");
    job.setRule((ResourcesPlugin.getWorkspace().getRoot()));
    job.schedule();
    job.addJobChangeListener(new JobChangeAdapter() {
      @Override
      public void done(IJobChangeEvent event) {
        if (event.getResult().getSeverity() == IStatus.OK) {
          showInEditor(job.internalFile);
        }
      }
    });
    return true;
  }

  private void showInEditor(final IFile file) {
    if (openModel.getValue()) {
      IItemType itemType = getItemType();
      ImageDescriptor icon = ImageDescriptor.createFromURL(itemType.getIconUrl());
      IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
      try {
        new ResourceEditorOpener(file, itemType.getUntitledName(), icon).openEditor(page);
      }
      catch (PartInitException e) {
        logger.error(NLS.bind(Messages.AbstractImportWizard_CouldNotOpen, file.getName()), e);
      }
    }
  }

  protected abstract IFile createInternalFile(String filename) throws CoreException;

  protected abstract void runImport(File externalFile, IFile internalFile, IProgressMonitor monitor)
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