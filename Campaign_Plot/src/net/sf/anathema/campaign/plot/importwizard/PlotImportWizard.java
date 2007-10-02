package net.sf.anathema.campaign.plot.importwizard;

import java.io.File;
import java.io.FileNotFoundException;

import net.disy.commons.core.model.BooleanModel;
import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.campaign.core.importwizard.AbstractImportWizard;
import net.sf.anathema.campaign.core.importwizard.FileSelectionWizardPage;
import net.sf.anathema.campaign.core.importwizard.IFileSelectionModel;
import net.sf.anathema.campaign.plot.creation.PlotRepositoryUtilities;
import net.sf.anathema.campaign.plot.creation.UnusedPlotFileFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.wizard.IWizardPage;

public class PlotImportWizard extends AbstractImportWizard {

  public PlotImportWizard() {
    super(new FolderSelectionStatusFactory());
  }

  @Override
  protected IWizardPage createImportPage(IFileSelectionModel fileModel, BooleanModel openModel) {
    return new FileSelectionWizardPage(fileModel, openModel, new PlotImportMessages());
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
  protected void runImport(File externalFile, IFile internalFile, IProgressMonitor monitor)
      throws CoreException,
      FileNotFoundException {
    // TODO Hierarchie erschaffen
    // TODO Alle Dateien aus dem externen Verzeichnis in den Workspace kopieren.
  }
}