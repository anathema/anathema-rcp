package net.sf.anathema.character.core.resource;

import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.character.CharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplateProvider;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;

import org.eclipse.core.resources.IContainer;

public final class CharacterDisplayNameProvider implements IDisplayNameProvider {
  private final IContainer characterFolder;
  private final ICharacterTemplateProvider provider;

  public CharacterDisplayNameProvider(IContainer characterFolder) {
    this(characterFolder, new CharacterTemplateProvider());
  }

  public CharacterDisplayNameProvider(IContainer characterFolder, ICharacterTemplateProvider provider) {
    this.characterFolder = characterFolder;
    this.provider = provider;
  }

  @Override
  public String getDisplayName() {
    return new CharacterPrintNameProvider().getPrintName(characterFolder, getFallbackName());
  }

  public String getFallbackName() {
    return provider.getTemplate(new CharacterId(characterFolder)).getName();
  }
}