package net.sf.anathema.create;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.resource.FileUtils;
import net.sf.anathema.basics.eclipse.runtime.IProvider;
import net.sf.anathema.basics.eclipse.runtime.StaticProvider;
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
    IProvider<String> provider = new StaticProvider<String>(TEMPLATE_NAME);
    factory.saveTemplate(characterFolder, provider);
    IFile file = getCharacterFolder().getFile("template.xml"); //$NON-NLS-1$
    document = DocumentUtilities.read(file.getContents());
  }

  @Test
  public void createsTemplateReference() throws Exception {
    assertEquals(TEMPLATE_NAME, getAttributeValue("reference")); //$NON-NLS-1$
  }

  @Test
  public void createsBundleVersion() throws Exception {
    String bundleVersion = BundlePersistenceUtilities.getBundleVersion(CharacterCorePlugin.ID);
    assertEquals(bundleVersion, getAttributeValue("bundleVersion")); //$NON-NLS-1$
  }

  private String getAttributeValue(String string) {
    return document.getRootElement().attributeValue(string);
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