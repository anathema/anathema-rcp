package net.sf.anathema.character.attributes.importwizard;

import static org.junit.Assert.assertTrue;
import net.sf.anathema.basics.eclipse.resource.FileUtils;
import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.character.core.create.CharacterRepositoryUtilities;
import net.sf.anathema.character.importwizard.utility.CharacterTestUtilities;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AttributeImporterTest {

  private final static String CHARACTER_NAME = "name"; //$NON-NLS-1$
  private static IFolder characterFolder;
  private AttributesImporter importer;

  @BeforeClass
  public static void createCharacterFolder() throws Exception {
    IProject project = RepositoryUtilities.getProject(CharacterRepositoryUtilities.getCharacterItemType());
    characterFolder = FileUtils.createUnusedFolder(project, CHARACTER_NAME);
    characterFolder.create(true, true, new NullProgressMonitor());
  }

  @Before
  public void createImporter() throws Exception {
    importer = new AttributesImporter();
  }

  @Test
  public void importsAttributes() throws Exception {
    Document document = AttributesDocumentObjectMother.createAttributesDocument();
    Document expecteddocument = AttributesDocumentObjectMother.createConvertedAttributesDocument();
    importer.runImport(characterFolder, document);
    Document resultdocument = DocumentUtilities.read(getCharacterFolder().getFile("attributes.model").getContents()); //$NON-NLS-1$
    CharacterTestUtilities.assertEqualWithoutWhitespace(expecteddocument, resultdocument);
  }

  @Test
  public void returnsOkStatus() throws Exception {
    Document document = AttributesDocumentObjectMother.createAttributesDocument();
    IStatus status = importer.runImport(characterFolder, document);
    assertTrue(status.isOK());
  }

  private static IFolder getCharacterFolder() {
    return CharacterTestUtilities.getCharacterFolder(CHARACTER_NAME);
  }

  @AfterClass
  public static void deleteCharacterFolder() throws CoreException {
    getCharacterFolder().delete(true, new NullProgressMonitor());
  }
}