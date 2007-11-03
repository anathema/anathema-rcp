package net.sf.anathema.character.core.importwizard;

import net.sf.anathema.basics.eclipse.resource.FileUtils;
import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.character.core.create.CharacterRepositoryUtilities;
import net.sf.anathema.character.importwizard.CharacterTemplateConverter;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TemplateConversionTest {
  private final static String CHARACTER_NAME = "name"; //$NON-NLS-1$
  private static IFolder characterFolder;
  private CharacterTemplateConverter converter;

  @BeforeClass
  public static void createCharacterFolder() throws Exception {
    IProject project = RepositoryUtilities.getProject(CharacterRepositoryUtilities.getCharacterItemType());
    characterFolder = FileUtils.createUnusedFolder(project, CHARACTER_NAME);
    characterFolder.create(true, true, new NullProgressMonitor());
  }

  @Before
  public void create() throws Exception {
    converter = new CharacterTemplateConverter(characterFolder);
  }

  @Test
  public void convertsTemplates() throws Exception {
    DocumentProvider documentProvider = new DocumentProvider(getClass());
    Document document = documentProvider.readDocument("oldcharacter.ecg"); //$NON-NLS-1$
    Document expecteddocument = documentProvider.readDocument("newtemplate.xml"); //$NON-NLS-1$
    converter.convert(document);
    Document resultdocument = DocumentUtilities.read(getCharacterFolder().getFile("template.xml").getContents()); //$NON-NLS-1$
    Assert.assertEquals(expecteddocument.asXML(), resultdocument.asXML());
  }

  @Test
  public void convertsDifferentTemplates() throws Exception {
    DocumentProvider documentProvider = new DocumentProvider(getClass());
    Document document = documentProvider.readDocument("oldcharacterdifferenttemplate.ecg"); //$NON-NLS-1$
    Document expecteddocument = documentProvider.readDocument("newtemplatedifferenttemplate.xml"); //$NON-NLS-1$
    converter.convert(document);
    Document resultdocument = DocumentUtilities.read(getCharacterFolder().getFile("template.xml").getContents()); //$NON-NLS-1$
    Assert.assertEquals(expecteddocument.asXML(), resultdocument.asXML());
  }

  @AfterClass
  public static void deleteCharacterFolder() throws CoreException {
    getCharacterFolder().delete(true, new NullProgressMonitor());
  }

  private static IFolder getCharacterFolder() {
    return ResourcesPlugin.getWorkspace().getRoot().getProject(
        CharacterRepositoryUtilities.getCharacterItemType().getProjectName()).getFolder(CHARACTER_NAME);
  }
}