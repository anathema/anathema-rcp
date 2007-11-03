package net.sf.anathema.character.core.create;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import net.sf.anathema.basics.eclipse.logging.ILogger;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.basics.eclipse.resource.FileUtils;
import net.sf.anathema.basics.eclipse.resource.FileWriter;
import net.sf.anathema.basics.eclipse.runtime.IProvider;
import net.sf.anathema.basics.eclipse.runtime.StaticProvider;
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

  private static final String TAG_TEMPLATE = "template"; //$NON-NLS-1$
  private static final ILogger logger = new Logger(CharacterCorePlugin.ID);

  public void createNewCharacter(String templateName, String folderName) {
    try {
      IProject project = RepositoryUtilities.getProject(CharacterRepositoryUtilities.getCharacterItemType());
      IFolder characterFolder = FileUtils.createUnusedFolder(project, folderName);
      characterFolder.create(true, true, new NullProgressMonitor());
      saveTemplate(characterFolder, new StaticProvider<String>(templateName));
    }
    catch (Exception e) {
      logger.error(Messages.NewCharacterActionDelegate_CharacterCreationError, e);
    }
  }

  public void saveTemplate(IContainer characterFolder, IProvider<String> provider) throws IOException, CoreException {
    Document document = BundlePersistenceUtilities.createVersionedDocument(TAG_TEMPLATE, CharacterCorePlugin.ID);
    Element rootElement = document.getRootElement();
    String staring = provider.get();
    rootElement.addAttribute(CharacterTemplateProvider.ATTRIB_REFERENCE, staring);
    IFile templateFile = characterFolder.getFile(new Path(CharacterTemplateProvider.TEMPLATE_FILE_NAME));
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    DocumentUtilities.save(document, outputStream);
    new FileWriter().saveToFile(templateFile, outputStream, new NullProgressMonitor());
  }
}