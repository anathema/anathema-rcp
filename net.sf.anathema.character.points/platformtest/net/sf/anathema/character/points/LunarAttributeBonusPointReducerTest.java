package net.sf.anathema.character.points;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.character.core.character.CharacterId;
import net.sf.anathema.character.core.create.CharacterRepositoryUtilities;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.points.configuration.internal.IPointConfiguration;
import net.sf.anathema.character.points.configuration.internal.PointConfigurationExtensionPoint;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LunarAttributeBonusPointReducerTest {

  private IFolder lunarFolder;

  @Before
  public void createLunarFolder() throws CoreException {
    IProject project = RepositoryUtilities.getProject(CharacterRepositoryUtilities.getCharacterItemType());
    lunarFolder = project.getFolder(new Path("MyLunar")); //$NON-NLS-1$
    lunarFolder.create(true, true, null);
  }

  @Test
  public void attributePrioritiesConsiderFavorization() throws Exception {
    copyResource(lunarFolder, "attributes.model", "case14Attributes.model"); //$NON-NLS-1$ //$NON-NLS-2$
    copyResource(lunarFolder, "template.xml", "lunarTemplate.xml"); //$NON-NLS-1$ //$NON-NLS-2$
    CharacterId characterId = new CharacterId(lunarFolder);
    Iterable<IPointConfiguration> all = new PointConfigurationExtensionPoint().getBonusPointConfigurations(new CharacterTemplateProvider().getTemplate(characterId));
    for (IPointConfiguration configuration : all) {
      if (configuration.getName().equals("Attributes")) { //$NON-NLS-1$
        assertEquals(9, configuration.getPoints(characterId));
        return;
      }
    }
    fail("No attribute points found"); //$NON-NLS-1$
  }

  private void copyResource(IFolder targetFolder, String targetName, String resourceName)
      throws CoreException,
      IOException {
    InputStream source = null;
    try {
      source = LunarAttributeBonusPointReducerTest.class.getResourceAsStream(resourceName);
      targetFolder.getFile(new Path(targetName)).create(source, true, null);
    }
    finally {
      if (source != null) {
        source.close();
      }
    }
  }

  @After
  public void deleteLunarFolder() throws CoreException {
    lunarFolder.delete(true, null);
  }
}