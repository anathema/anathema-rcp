package net.sf.anathema.character.core.resource;

import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.character.CharacterId;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplateProvider;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;

public final class CharacterDisplayNameProvider implements IDisplayNameProvider {
  private final IContainer characterFolder;
  private final ICharacterTemplateProvider provider;

  public static IDisplayNameProvider CreateFrom(IModelIdentifier modelIdentifier) {
    ICharacterId characterId = modelIdentifier.getCharacterId();
    IFolder characterFolder = (IFolder) characterId.getAdapter(IFolder.class);
    return new CharacterDisplayNameProvider(characterFolder);
  }

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