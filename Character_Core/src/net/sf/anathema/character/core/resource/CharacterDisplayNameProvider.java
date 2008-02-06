package net.sf.anathema.character.core.resource;

import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.character.CharacterId;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;

import org.eclipse.core.resources.IContainer;

public final class CharacterDisplayNameProvider implements IDisplayNameProvider {
  private final IContainer characterFolder;

  public CharacterDisplayNameProvider(IContainer characterFolder) {
    this.characterFolder = characterFolder;
  }

  @Override
  public String getDisplayName() {
    String fallback = new CharacterTemplateProvider().getTemplate(new CharacterId(characterFolder)).getName();
    return new CharacterPrintNameProvider().getPrintName(characterFolder, fallback);
  }
}