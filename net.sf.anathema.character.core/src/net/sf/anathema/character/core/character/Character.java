package net.sf.anathema.character.core.character;

import net.sf.anathema.character.core.resource.CharacterDisplayNameProvider;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.core.type.CharacterTypeFinder;

import org.eclipse.core.resources.IContainer;

public class Character implements ICharacter {

  public static ICharacter From(ICharacterId characterId, IModelCollection modelCollection) {
    ICharacterTemplateProvider templateProvider = new CharacterTemplateProvider();
    ICharacterTypeFinder characterTypeFinder = new CharacterTypeFinder();
    return new Character(characterId, modelCollection, templateProvider, characterTypeFinder);
  }

  private final ICharacterId characterId;
  private final IModelCollection modelProvider;
  private final ICharacterTemplateProvider templateProvider;
  private final ICharacterTypeFinder characterTypeFinder;

  public Character(
      ICharacterId characterId,
      IModelCollection modelProvider,
      ICharacterTemplateProvider templateProvider,
      ICharacterTypeFinder characterTypeFinder) {
    this.characterId = characterId;
    this.modelProvider = modelProvider;
    this.templateProvider = templateProvider;
    this.characterTypeFinder = characterTypeFinder;
  }

  public IModel getModel(String modelId) {
    return modelProvider.getModel(new ModelIdentifier(characterId, modelId));
  }

  @Override
  public ICharacterTemplate getTemplate() {
    return templateProvider.getTemplate(characterId);
  }

  @Override
  public ICharacterType getCharacterType() {
    return characterTypeFinder.getCharacterType(characterId);
  }

  @Override
  public String getDisplayName() {
    IContainer container = (IContainer) characterId.getAdapter(IContainer.class);
    return new CharacterDisplayNameProvider(container).getDisplayName();
  }

  @Override
  public ICharacterId getId() {
    return characterId;
  }
}