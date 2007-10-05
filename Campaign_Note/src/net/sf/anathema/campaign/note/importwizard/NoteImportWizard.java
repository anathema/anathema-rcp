package net.sf.anathema.campaign.note.importwizard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import net.disy.commons.core.model.BooleanModel;
import net.sf.anathema.basics.repository.input.UnusedFileFactory;
import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.campaign.core.importwizard.AbstractImportWizard;
import net.sf.anathema.campaign.core.importwizard.FileSelectionWizardPage;
import net.sf.anathema.campaign.core.importwizard.IFileSelectionModel;
import net.sf.anathema.campaign.note.NotesRepositoryUtilities;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.wizard.IWizardPage;

public class NoteImportWizard extends AbstractImportWizard {

  public NoteImportWizard() {
    super(new FileSelectionStatusFactory());
  }

  @Override
  protected IFile createInternalFile(String filename) throws CoreException {
    return new UnusedFileFactory(getItemType()).createUnusedFile(filename);
  }

  @Override
  protected IItemType getItemType() {
    return NotesRepositoryUtilities.getNotesItemType();
  }

  @Override
  protected IWizardPage createImportPage(IFileSelectionModel fileModel, BooleanModel openModel) {
    return new FileSelectionWizardPage(fileModel, openModel, new NoteImportMessages(), new OpenNoteDialog());
  }

  @Override
  protected void runImport(final File externalFile, final IFile internalFile, IProgressMonitor monitor)
      throws CoreException,
      FileNotFoundException {
    internalFile.create(new FileInputStream(externalFile), true, monitor);
  }

  @Override
  protected void undoImport(IFile internalFile, IProgressMonitor monitor) throws CoreException {
    internalFile.delete(true, monitor);
  }
}