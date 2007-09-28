package net.sf.anathema.campaign.note.importwizard;

import java.io.File;
import java.io.FileInputStream;

import net.disy.commons.core.model.BooleanModel;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.basics.repository.input.UnusedFileFactory;
import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.basics.repository.treecontent.itemtype.ResourceEditorOpener;
import net.sf.anathema.campaign.note.NotesRepositoryUtilities;
import net.sf.anathema.campaign.note.plugin.INotePluginConstants;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;

public class NoteImportWizard extends Wizard implements IImportWizard {
  private final IFileSelectionModel model = new FileSelectionModel();
  private final BooleanModel openModel = new BooleanModel();
  private IWorkbench workbench;

  @Override
  public boolean canFinish() {
    return model.isComplete();
  }

  @Override
  public boolean performFinish() {
    File externalFile = model.getFile();
    IItemType itemType = NotesRepositoryUtilities.getNotesItemType();
    try {
      // TODO ProgressMonitor
      IProgressMonitor monitor = new NullProgressMonitor();
      IFile file = new UnusedFileFactory(itemType).createUnusedFile(externalFile.getName());
      file.create(new FileInputStream(externalFile), true, monitor);
      if (openModel.getValue()) {
        ImageDescriptor icon = ImageDescriptor.createFromURL(itemType.getIconUrl());
        IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
        new ResourceEditorOpener(file, itemType.getUntitledName(), icon).openEditor(page);
      }
      return true;
    }
    catch (Exception e) {
      new Logger(INotePluginConstants.PLUGIN_ID).error(Messages.NoteImportWizard_ErrorMessage, e);
      return false;
    }
  }

  @Override
  public void init(IWorkbench currentWorkbench, IStructuredSelection selection) {
    this.workbench = currentWorkbench;
    setWindowTitle(Messages.NoteImportWizard_WindowTitle);
    addPage(new NoteFileSelectionWizardPage(model, openModel));
  }
}