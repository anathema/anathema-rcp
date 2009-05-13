package net.sf.anathema.character.core.create;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import net.disy.commons.core.provider.IProvider;
import net.sf.anathema.basics.eclipse.logging.ILogger;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.basics.eclipse.resource.FileUtils;
import net.sf.anathema.basics.eclipse.resource.FileWriter;
import net.sf.anathema.basics.item.persistence.BundlePersistenceUtilities;
import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.character.core.plugin.internal.CharacterCorePlugin;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.dom4j.Element;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;

public class CharacterFactory {

  private static final IFolder NO_FOLDER = null;
  private static final String TAG_TEMPLATE = "template"; //$NON-NLS-1$
  private static final ILogger logger = new Logger(CharacterCorePlugin.ID);

  //TODO Der R�ckgabewert wird nur f�r Tests gebraucht. Vielleicht kann man ihn (und damit die Null-Referenz) loswerden?
  public IFolder createNewCharacter(String templateName, String folderName) {
    try {
      IProject project = RepositoryUtilities.getProject(CharacterRepositoryUtilities.getCharacterItemType());
      IFolder characterFolder = FileUtils.createUnusedFolder(project, folderName);
      characterFolder.create(true, true, new NullProgressMonitor());
      saveTemplate(characterFolder, new net.disy.commons.core.provider.StaticProvider<String>(templateName));
      return characterFolder;
    }
    catch (Exception e) {
      logger.error(Messages.NewCharacterActionDelegate_CharacterCreationError, e);
      return NO_FOLDER;
    }
  }

  public void saveTemplate(IContainer characterFolder, IProvider<String> provider) throws IOException, CoreException {
    Document document = new BundlePersistenceUtilities().createDocument(TAG_TEMPLATE, CharacterCorePlugin.ID);
    Element rootElement = document.getRootElement();
    String staring = provider.getObject();
    rootElement.addAttribute(CharacterTemplateProvider.ATTRIB_REFERENCE, staring);
    IFile templateFile = characterFolder.getFile(new Path(CharacterTemplateProvider.TEMPLATE_FILE_NAME));
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    DocumentUtilities.save(document, outputStream);
    new FileWriter().saveToFile(templateFile, outputStream, new NullProgressMonitor());
  }
}