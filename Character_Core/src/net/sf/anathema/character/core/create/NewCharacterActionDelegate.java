package net.sf.anathema.character.core.create;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.basics.eclipse.resource.FileWriter;
import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.character.core.plugin.ICharacterCorePluginConstants;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class NewCharacterActionDelegate implements IObjectActionDelegate {

  private static final Logger logger = new Logger(ICharacterCorePluginConstants.PLUGIN_ID);
  
  @Override
  public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    // nothing to do
  }

  @Override
  public void run(IAction action) {
    createNewCharacter("net.sf.anathema.charactertype.heroicmortal");
  }

  public static void createNewCharacter(String templateName) {
    try {
      IProject project = RepositoryUtilities.getProject(CharacterRepositoryUtilities.getCharacterItemType());
      IFolder characterFolder = createUnusedFolder(project, "Unnamed");
      characterFolder.create(true, true, new NullProgressMonitor());
      saveTemplate(characterFolder, templateName);
    }
    catch (Exception e) {
      logger.error(
          Messages.NewCharacterActionDelegate_CharacterCreationError,
          e);
    }
  }

  private static void saveTemplate(IFolder characterFolder, String templateName) throws IOException, CoreException {
    Element rootElement = DocumentHelper.createElement("template"); //$NON-NLS-1$
    Document document = DocumentHelper.createDocument(rootElement);
    rootElement.addAttribute(CharacterTemplateProvider.ATTRIB_REFERENCE, templateName);
    IFile templateFile = characterFolder.getFile(CharacterTemplateProvider.TEMPLATE_FILE_NAME);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    DocumentUtilities.save(document, outputStream);
    new FileWriter().saveToFile(templateFile, outputStream, new NullProgressMonitor());
  }

  private static IFolder createUnusedFolder(IProject project, String suggestedFolderName) {
    int count = 0;
    IFolder folder = project.getFolder(suggestedFolderName);
    while (folder.exists()) {
      folder = project.getFolder(suggestedFolderName + ++count);
    }
    return folder;
  }

  @Override
  public void selectionChanged(IAction action, ISelection selection) {
    // nothing to do
  }
}