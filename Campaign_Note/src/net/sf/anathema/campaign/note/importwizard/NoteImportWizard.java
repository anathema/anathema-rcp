package net.sf.anathema.campaign.note.importwizard;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;

import net.disy.commons.core.model.BooleanModel;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.basics.repository.input.UnusedFileFactory;
import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.basics.repository.treecontent.itemtype.ResourceEditorOpener;
import net.sf.anathema.campaign.note.NotesRepositoryUtilities;
import net.sf.anathema.campaign.note.plugin.INotePluginConstants;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class NoteImportWizard extends Wizard implements IImportWizard {
  private final Logger logger = new Logger(INotePluginConstants.PLUGIN_ID);
  private final IFileSelectionModel model = new FileSelectionModel();
  private final BooleanModel openModel = new BooleanModel();
  private IWorkbench workbench;

  @Override
  public boolean canFinish() {
    return model.isComplete();
  }

  @Override
  public boolean performFinish() {
    final File externalFile = model.getFile();
    IItemType itemType = NotesRepositoryUtilities.getNotesItemType();
    try {
      final IFile internalFile = new UnusedFileFactory(itemType).createUnusedFile(externalFile.getName());
      importFile(externalFile, internalFile);
      showInEditor(itemType, internalFile);
      return true;
    }
    catch (Exception e) {
      logger.error(Messages.NoteImportWizard_ImportNoteError, e);
      return false;
    }
  }

  private void importFile(final File externalFile, final IFile internalFile)
      throws InvocationTargetException,
      InterruptedException {
    workbench.getActiveWorkbenchWindow().run(true, false, new IRunnableWithProgress() {
      @Override
      public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        try {
          internalFile.create(new FileInputStream(externalFile), true, monitor);
        }
        catch (Exception e) {
          logger.error(Messages.NoteImportWizard_CreateFileError, e);
          throw new InvocationTargetException(e);
        }
      }
    });
  }

  private void showInEditor(IItemType itemType, final IFile file) throws PartInitException {
    if (openModel.getValue()) {
      ImageDescriptor icon = ImageDescriptor.createFromURL(itemType.getIconUrl());
      IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
      new ResourceEditorOpener(file, itemType.getUntitledName(), icon).openEditor(page);
    }
  }

  @Override
  public void init(IWorkbench currentWorkbench, IStructuredSelection selection) {
    this.workbench = currentWorkbench;
    setWindowTitle(Messages.NoteImportWizard_WindowTitle);
    addPage(new NoteFileSelectionWizardPage(model, openModel));
  }
}