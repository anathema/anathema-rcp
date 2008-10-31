package net.sf.anathema.character.importwizard.internal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import net.disy.commons.core.model.BooleanModel;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.basics.eclipse.resource.FileUtils;
import net.sf.anathema.basics.importexport.AbstractImportWizard;
import net.sf.anathema.basics.importexport.FileSelectionWizardPage;
import net.sf.anathema.basics.importexport.IFileSelectionModel;
import net.sf.anathema.basics.importexport.ImportFileSelectionStatusFactory;
import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.character.core.character.CharacterId;
import net.sf.anathema.character.core.create.CharacterRepositoryUtilities;
import net.sf.anathema.character.core.model.ModelExtensionPoint;
import net.sf.anathema.character.core.repository.IModelDisplayConfiguration;
import net.sf.anathema.character.core.resource.CharacterModelEditorOpener;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.importwizard.IModelImporter;
import net.sf.anathema.character.importwizard.plugin.CharacterImportWizardPluginConstants;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class CharacterImportWizard extends AbstractImportWizard {
  private static final Logger logger = new Logger(CharacterImportWizardPluginConstants.PLUGIN_ID);

  public CharacterImportWizard() {
    super(new ImportFileSelectionStatusFactory());
  }

  @Override
  protected IWizardPage createImportPage(IFileSelectionModel filemodel, BooleanModel openmodel) {
    return new FileSelectionWizardPage(filemodel, openmodel, new CharacterImportMessages(), new OpenCharacterDialog());
  }

  @Override
  protected IFile createInternalFile(String filename) throws CoreException {
    IProject project = RepositoryUtilities.getProject(CharacterRepositoryUtilities.getCharacterItemType());
    IFolder characterFolder = FileUtils.createUnusedFolder(project, filename);
    characterFolder.create(true, true, new NullProgressMonitor());
    return characterFolder.getFile(CharacterTemplateProvider.TEMPLATE_FILE_NAME);
  }

  @Override
  protected IItemType getItemType() {
    return CharacterRepositoryUtilities.getCharacterItemType();
  }

  @Override
  protected IStatus runImport(File externalFile, IFile internalFile, IProgressMonitor monitor)
      throws CoreException,
      FileNotFoundException {
    try {
      Document document = DocumentUtilities.read(externalFile);
      IContainer container = internalFile.getParent();
      IStatus status = new TemplateImporter().runImport(container, document);
      if (status.isOK()) {
        for (IModelImporter importer : new ModelImporterExtensionPoint().getImporters()) {
          importer.runImport(container, document);
        }
      }
      return status;
    }
    catch (PersistenceException e) {
      throw new CoreException(logger.createErrorStatus(
          net.sf.anathema.character.importwizard.internal.Messages.CharacterImportWizard_ConversionError,
          e));
    }
    catch (IOException e) {
      throw new CoreException(logger.createErrorStatus(
          net.sf.anathema.character.importwizard.internal.Messages.CharacterImportWizard_ConversionError,
          e));
    }
  }

  @Override
  protected void openEditor(final IFile file, IWorkbenchPage page) throws PartInitException {
    IContainer container = file.getParent();
    ModelExtensionPoint modelExtensionPoint = new ModelExtensionPoint();
    Iterable<String> filenames = modelExtensionPoint.getModelFileNames(
        new CharacterId(container),
        new CharacterTemplateProvider());
    for (String filename : filenames) {
      IModelDisplayConfiguration configuration = modelExtensionPoint.getDisplayConfiguration(container.getFile(new Path(
          filename)));
      if (configuration != null) {
        new CharacterModelEditorOpener().openEditor(page, container, configuration);
        return;
      }
    }
  }

  @Override
  protected void undoImport(IFile internalFile, IProgressMonitor monitor) throws CoreException {
    internalFile.getParent().delete(true, monitor);
  }
}