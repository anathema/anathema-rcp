package net.sf.anathema.character.importwizard;

import net.sf.anathema.basics.eclipse.resource.FileUtils;
import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.character.core.create.CharacterRepositoryUtilities;
import net.sf.anathema.character.importwizard.internal.TemplateImporter;
import net.sf.anathema.character.importwizard.utility.CharacterTestUtilities;
import net.sf.anathema.character.importwizard.utility.ImportDocumentObjectMother;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TemplateConversionTest {
  private final static String CHARACTER_NAME = "name"; //$NON-NLS-1$
  private static IFolder characterFolder;
  private TemplateImporter converter;

  @BeforeClass
  public static void createCharacterFolder() throws Exception {
    IProject project = RepositoryUtilities.getProject(CharacterRepositoryUtilities.getCharacterItemType());
    characterFolder = FileUtils.createUnusedFolder(project, CHARACTER_NAME);
    characterFolder.create(true, true, new NullProgressMonitor());
  }

  @Before
  public void create() throws Exception {
    converter = new TemplateImporter();
  }

  @Test
  public void convertsTemplates() throws Exception {
    Document document = ImportDocumentObjectMother.createDocumentForTemplate("Solar", "TemplateType.Default"); //$NON-NLS-1$ //$NON-NLS-2$
    Document expecteddocument = ImportDocumentObjectMother.getDocumentFromFile(
        getClass(),
        "net/sf/anathema/character/importwizard/", //$NON-NLS-1$
        "newtemplate.xml"); //$NON-NLS-1$
    converter.runImport(characterFolder, document);
    Document resultdocument = DocumentUtilities.read(getCharacterFolder().getFile("template.xml").getContents()); //$NON-NLS-1$
    Assert.assertEquals(expecteddocument.asXML(), resultdocument.asXML());
  }

  @Test
  public void convertsDifferentTemplates() throws Exception {
    Document document = ImportDocumentObjectMother.createDocumentForTemplate("Sidereal", "TemplateType.Default"); //$NON-NLS-1$ //$NON-NLS-2$
    Document expecteddocument = ImportDocumentObjectMother.getDocumentFromFile(
        getClass(),
        "net/sf/anathema/character/importwizard/", //$NON-NLS-1$
        "newtemplatedifferenttemplate.xml"); //$NON-NLS-1$
    converter.runImport(characterFolder, document);
    Document resultdocument = DocumentUtilities.read(getCharacterFolder().getFile("template.xml").getContents()); //$NON-NLS-1$
    Assert.assertEquals(expecteddocument.asXML(), resultdocument.asXML());
  }

  @Test
  public void statusOkForSupportedTemplate() throws Exception {
    Document document = ImportDocumentObjectMother.createDocumentForTemplate("Sidereal", "TemplateType.Default"); //$NON-NLS-1$ //$NON-NLS-2$
    IStatus status = converter.runImport(characterFolder, document);
    Assert.assertEquals(IStatus.OK, status.getSeverity());
  }

  @Test
  public void warnsForUnsupportedTemplate() throws Exception {
    Document document = ImportDocumentObjectMother.createDocumentForTemplate("Sidereal", "Narf"); //$NON-NLS-1$ //$NON-NLS-2$
    IStatus status = converter.runImport(characterFolder, document);
    Assert.assertEquals(IStatus.WARNING, status.getSeverity());
  }

  @AfterClass
  public static void deleteCharacterFolder() throws CoreException {
    getCharacterFolder().delete(true, new NullProgressMonitor());
  }

  private static IFolder getCharacterFolder() {
    return CharacterTestUtilities.getCharacterFolder(CHARACTER_NAME);
  }
}