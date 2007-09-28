package net.sf.anathema.campaign.note.importwizard;

import java.io.File;
import java.io.FileInputStream;

import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.basics.repository.input.UnusedFileFactory;
import net.sf.anathema.campaign.note.NotesRepositoryUtilities;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

public class NoteImportWizard extends Wizard implements IImportWizard {
  private final IFileSelectionModel model = new FileSelectionModel();

  @Override
  public boolean canFinish() {
    return model.isComplete();
  }

  @Override
  public boolean performFinish() {
    File externalFile = model.getFile();
    try {
      // TODO ProgressMonitor
      IFile file = new UnusedFileFactory(NotesRepositoryUtilities.getNotesItemType()).createUnusedFile(externalFile.getName());
      file.create(new FileInputStream(externalFile), true, new NullProgressMonitor());
      return true;
    }
    catch (Exception e) {
      new Logger("net.sf.anathema.campaign.note").error("Could not import Note.", e);
      return false;
    }
    // TODO: Open when finished
  }

  @Override
  public void init(IWorkbench workbench, IStructuredSelection selection) {
    setWindowTitle("Anathema Classic Note");
    addPage(new NoteFileSelectionWizardPage(model));
  }
}