package net.sf.anathema.campaign.plot.importwizard;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import net.disy.commons.core.model.BooleanModel;
import net.sf.anathema.basics.importexport.AbstractImportWizard;
import net.sf.anathema.basics.importexport.FileSelectionWizardPage;
import net.sf.anathema.basics.importexport.IFileSelectionModel;
import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.basics.repository.treecontent.itemtype.ResourceEditorOpener;
import net.sf.anathema.campaign.plot.creation.PlotRepositoryUtilities;
import net.sf.anathema.campaign.plot.creation.UnusedPlotFileFactory;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class PlotImportWizard extends AbstractImportWizard {

  public PlotImportWizard() {
    super(new FolderSelectionStatusFactory());
  }

  @Override
  protected IWizardPage createImportPage(IFileSelectionModel fileModel, BooleanModel openModel) {
    return new FileSelectionWizardPage(fileModel, openModel, new PlotImportMessages(), new OpenFolderDialog());
  }

  @Override
  protected IFile createInternalFile(String filename) throws CoreException {
    return new UnusedPlotFileFactory().createUnusedFile(filename);
  }

  @Override
  protected IItemType getItemType() {
    return PlotRepositoryUtilities.getPlotItemType();
  }

  @Override
  protected void openEditor(final IFile file, IWorkbenchPage page) throws PartInitException {
    new ResourceEditorOpener(file, getItemType().getUntitledName(), getItemType().getIconUrl()).openEditor(page);
  }

  @Override
  protected IStatus runImport(File externalFile, IFile internalFile, IProgressMonitor monitor)
      throws CoreException,
      FileNotFoundException {
    monitor.subTask(Messages.PlotImportWizard_CopySubTask);
    File[] seriesFiles = externalFile.listFiles(new FileFilter() {
      @Override
      public boolean accept(File file) {
        return file.getName().endsWith("." + getItemType().getFileExtension()); //$NON-NLS-1$
      }
    });
    IContainer parentFolder = internalFile.getParent();
    for (File file : seriesFiles) {
      IFile importFile = parentFolder.getFile(new Path(file.getName()));
      importFile.create(new FileInputStream(file), true, monitor);
    }
    monitor.worked(1);
    new LegacyMainFileConverter().convert(externalFile, internalFile, monitor);
    return Status.OK_STATUS;
  }

  @Override
  protected void undoImport(IFile internalFile, IProgressMonitor monitor) throws CoreException {
    internalFile.getParent().delete(true, monitor);
  }
}