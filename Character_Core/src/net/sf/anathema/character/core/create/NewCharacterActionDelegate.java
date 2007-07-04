package net.sf.anathema.character.core.create;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import net.sf.anathema.basics.eclipse.resource.FileWriter;
import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.character.core.CharacterCorePlugin;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class NewCharacterActionDelegate implements IObjectActionDelegate {

  @Override
  public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    // nothing to do
  }

  @Override
  public void run(IAction action) {
    try {
      String templateName = CharacterTemplateProvider.STATIC_TEMPLATE_ID;
      IProject project = RepositoryUtilities.getProject(CharacterRepositoryUtilities.getCharacterItemType());
      IFolder characterFolder = createUnusedFolder(project, "Unnamed");
      characterFolder.create(true, true, new NullProgressMonitor());
      saveTemplate(characterFolder, templateName);
    }
    catch (Exception e) {
      CharacterCorePlugin.getDefaultInstance().log(IStatus.ERROR, "Error creating Character", e);
    }
  }

  private void saveTemplate(IFolder characterFolder, String templateName) throws IOException, CoreException {
    Element rootElement = DocumentHelper.createElement("template");
    Document document = DocumentHelper.createDocument(rootElement);
    rootElement.addAttribute("reference", templateName);
    IFile templateFile = characterFolder.getFile("template.xml");
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    DocumentUtilities.save(document, outputStream);
    new FileWriter().saveToFile(templateFile, outputStream, new NullProgressMonitor());
  }

  private IFolder createUnusedFolder(IProject project, String suggestedFolderName) {
    int count = 0;
    IFolder folder = project.getFolder(suggestedFolderName);
    while(folder.exists()) {
      folder = project.getFolder(suggestedFolderName + ++count);
    }
    return folder;
  }

  @Override
  public void selectionChanged(IAction action, ISelection selection) {
    // nothing to do
  }
}