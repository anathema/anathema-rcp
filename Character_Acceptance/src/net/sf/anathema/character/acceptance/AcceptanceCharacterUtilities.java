package net.sf.anathema.character.acceptance;

import net.sf.anathema.character.core.create.CharacterFactory;

import org.eclipse.core.resources.IFolder;

public class AcceptanceCharacterUtilities {

  public static IFolder createCharacterFolder(String templateName, String folderName) {
    return new CharacterFactory().createNewCharacter(templateName, folderName);
  }
}
