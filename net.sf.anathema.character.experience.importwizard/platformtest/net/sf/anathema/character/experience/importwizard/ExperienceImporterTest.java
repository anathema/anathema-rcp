package net.sf.anathema.character.experience.importwizard;

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

public class ExperienceImporterTest {
  private final static String CHARACTER_NAME = "name"; //$NON-NLS-1$
  private static IFolder characterFolder;
  private ExperienceImporter importer;

  @BeforeClass
  public static void createCharacterFolder() throws Exception {
    IProject project = RepositoryUtilities.getProject(CharacterRepositoryUtilities.getCharacterItemType());
    characterFolder = FileUtils.createUnusedFolder(project, CHARACTER_NAME);
    characterFolder.create(true, true, new NullProgressMonitor());
  }

  @Before
  public void create() throws Exception {
    importer = new ExperienceImporter();
  }

  @Test
  public void importsDescription() throws Exception {
    Document document = ExperienceDocumentObjectMother.createExperienceDocument();
    Document expecteddocument = ExperienceDocumentObjectMother.createConvertedExperienceDocument();
    importer.runImport(characterFolder, document);
    Document resultdocument = DocumentUtilities.read(getCharacterFolder().getFile("experience.model").getContents()); //$NON-NLS-1$
    CharacterTestUtilities.assertEqualWithoutWhitespace(expecteddocument, resultdocument);
  }

  private static IFolder getCharacterFolder() {
    return CharacterTestUtilities.getCharacterFolder(CHARACTER_NAME);
  }

  @Test
  public void returnsStatusOk() throws Exception {
    Document document = ExperienceDocumentObjectMother.createExperienceDocument();
    IStatus status = importer.runImport(characterFolder, document);
    assertTrue(status.isOK());
  }

  @AfterClass
  public static void deleteCharacterFolder() throws CoreException {
    getCharacterFolder().delete(true, new NullProgressMonitor());
  }
}