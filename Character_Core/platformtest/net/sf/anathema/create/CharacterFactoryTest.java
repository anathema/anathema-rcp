package net.sf.anathema.create;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.resource.FileUtils;
import net.sf.anathema.basics.item.persistence.BundlePersistenceUtilities;
import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.character.core.create.CharacterFactory;
import net.sf.anathema.character.core.create.CharacterRepositoryUtilities;
import net.sf.anathema.character.core.plugin.internal.CharacterCorePlugin;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CharacterFactoryTest {

  private static final String TEMPLATE_NAME = "templateName"; //$NON-NLS-1$
  private final static String CHARACTER_NAME = "name"; //$NON-NLS-1$
  private static IFolder characterFolder;
  private Document document;

  @BeforeClass
  public static void createCharacterFolder() throws Exception {
    IProject project = RepositoryUtilities.getProject(CharacterRepositoryUtilities.getCharacterItemType());
    characterFolder = FileUtils.createUnusedFolder(project, CHARACTER_NAME);
    characterFolder.create(true, true, new NullProgressMonitor());
  }

  @Before
  public void createFactory() throws Exception {
    CharacterFactory factory = new CharacterFactory();
    factory.saveTemplate(characterFolder, TEMPLATE_NAME);
    IFile file = getCharacterFolder().getFile("template.xml"); //$NON-NLS-1$
    document = DocumentUtilities.read(file.getContents());
  }

  @Test
  public void createsTemplateReference() throws Exception {
    String reference = document.getRootElement().attributeValue("reference"); //$NON-NLS-1$
    assertEquals(TEMPLATE_NAME, reference);
  }

  @Test
  public void createsBundleVersion() throws Exception {
    String version = document.getRootElement().attributeValue("bundleVersion"); //$NON-NLS-1$
    assertEquals(BundlePersistenceUtilities.getBundleVersion(CharacterCorePlugin.ID), version);
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