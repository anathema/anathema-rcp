package net.sf.anathema.character.core.resource;

import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;

import org.eclipse.core.resources.IContainer;

public final class CharacterDisplayNameProvider implements IDisplayNameProvider {
  private final IContainer characterFolder;

  public CharacterDisplayNameProvider(IContainer characterFolder) {
    this.characterFolder = characterFolder;
  }

  @Override
  public String getDisplayName() {
    return new CharacterPrintNameProvider().getPrintName(characterFolder, characterFolder.getName());
  }
}