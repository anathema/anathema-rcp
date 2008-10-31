package net.sf.anathema.character.importwizard.utility;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.character.core.create.CharacterRepositoryUtilities;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.ResourcesPlugin;

public class CharacterTestUtilities {
  public static IFolder getCharacterFolder(String name) {
    return ResourcesPlugin.getWorkspace().getRoot().getProject(
        CharacterRepositoryUtilities.getCharacterItemType().getProjectName()).getFolder(name);
  }

  public static void assertEqualWithoutWhitespace(Document expecteddocument, Document resultdocument) {
    assertEquals(removeWhitespace(expecteddocument), removeWhitespace(resultdocument));
  }

  private static String removeWhitespace(Document expecteddocument2) {
    return DocumentUtilities.asString(expecteddocument2).replaceAll("\\s", ""); //$NON-NLS-1$ //$NON-NLS-2$
  }
}