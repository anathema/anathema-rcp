package net.sf.anathema.character.importwizard;

import net.sf.anathema.basics.eclipse.resource.FileUtils;
import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.character.core.create.CharacterRepositoryUtilities;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CharacterDescriptionImporterTest {
  private final static String CHARACTER_NAME = "name"; //$NON-NLS-1$
  private static IFolder characterFolder;
  private CharacterDescriptionImporter importer;

  @BeforeClass
  public static void createCharacterFolder() throws Exception {
    IProject project = RepositoryUtilities.getProject(CharacterRepositoryUtilities.getCharacterItemType());
    characterFolder = FileUtils.createUnusedFolder(project, CHARACTER_NAME);
    characterFolder.create(true, true, new NullProgressMonitor());
  }

  @Before
  public void create() throws Exception {
    importer = new CharacterDescriptionImporter(characterFolder);
  }

  @Test
  public void importsDescription() throws Exception {
    Document document = ImportDocumentObjectMother.createEmptyDescriptionDocument();
    Document expecteddocument = ImportDocumentObjectMother.createEmptyVersionedModelDocument();
    importer.runImport(new CharacterDescriptionConverter().convert(document));
    Document resultdocument = DocumentUtilities.read(getCharacterFolder().getFile("basic.description").getContents()); //$NON-NLS-1$
    Assert.assertEquals(expecteddocument.asXML(), resultdocument.asXML());
  }

  private static IFolder getCharacterFolder() {
    return CharacterTestUtilities.getCharacterFolder(CHARACTER_NAME);
  }

  @AfterClass
  public static void deleteCharacterFolder() throws CoreException {
    getCharacterFolder().delete(true, new NullProgressMonitor());
  }
}