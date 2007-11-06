package net.sf.anathema.character.importwizard.utility;

import net.sf.anathema.character.core.create.CharacterRepositoryUtilities;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.ResourcesPlugin;

public class CharacterTestUtilities {
  public static IFolder getCharacterFolder(String name) {
    return ResourcesPlugin.getWorkspace().getRoot().getProject(
        CharacterRepositoryUtilities.getCharacterItemType().getProjectName()).getFolder(name);
  }
}