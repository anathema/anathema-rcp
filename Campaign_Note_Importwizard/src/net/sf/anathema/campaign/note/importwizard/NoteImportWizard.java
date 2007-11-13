package net.sf.anathema.campaign.note.importwizard;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import net.disy.commons.core.model.BooleanModel;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.basics.importwizard.AbstractImportWizard;
import net.sf.anathema.basics.importwizard.FileSelectionStatusFactory;
import net.sf.anathema.basics.importwizard.FileSelectionWizardPage;
import net.sf.anathema.basics.importwizard.IFileSelectionModel;
import net.sf.anathema.basics.item.persistence.BundlePersistenceUtilities;
import net.sf.anathema.basics.repository.input.UnusedFileFactory;
import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.basics.repository.treecontent.itemtype.ResourceEditorOpener;
import net.sf.anathema.campaign.note.NotesRepositoryUtilities;
import net.sf.anathema.campaign.note.plugin.NotePluginConstants;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class NoteImportWizard extends AbstractImportWizard {
  private static final Logger logger = new Logger(NotePluginConstants.PLUGIN_ID);

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
  protected void openEditor(final IFile file, IWorkbenchPage page) throws PartInitException {
    new ResourceEditorOpener(file, getItemType().getUntitledName(), getItemType().getIconUrl()).openEditor(page);
  }

  @Override
  protected IStatus runImport(final File externalFile, final IFile internalFile, IProgressMonitor monitor)
      throws CoreException,
      FileNotFoundException {
    try {
      Document document = DocumentUtilities.read(externalFile);
      new BundlePersistenceUtilities().addBundleVersionAttribute(
          document.getRootElement(),
          NotePluginConstants.PLUGIN_ID);
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      DocumentUtilities.save(document, outputStream);
      internalFile.create(new ByteArrayInputStream(outputStream.toByteArray()), true, monitor);
      return Status.OK_STATUS;
    }
    catch (PersistenceException e) {
      throw new CoreException(logger.createErrorStatus(Messages.NoteImportWizard_ConversionError, e));
    }
    catch (IOException e) {
      throw new CoreException(logger.createErrorStatus(Messages.NoteImportWizard_ConversionError, e));
    }
  }

  @Override
  protected void undoImport(IFile internalFile, IProgressMonitor monitor) throws CoreException {
    internalFile.delete(true, monitor);
  }
}