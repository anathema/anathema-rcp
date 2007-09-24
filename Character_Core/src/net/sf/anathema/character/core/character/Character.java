package net.sf.anathema.character.core.character;

import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModel;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.core.type.CharacterTypeFinder;
import net.sf.anathema.character.core.type.ICharacterType;

public class Character implements ICharacter {

  private final ICharacterId characterId;
  private final IModelProvider modelProvider;

  public Character(ICharacterId characterId, IModelProvider modelProvider) {
    this.characterId = characterId;
    this.modelProvider = modelProvider;
  }
  
  public IModel getModel(String modelId) {
    return modelProvider.getModel(new ModelIdentifier(characterId, modelId));
  }

  @Override
  public String getTemplateId() {
    return new CharacterTemplateProvider().getTemplate(characterId).getId();
  }

  @Override
  public ICharacterType getCharacterType() {
    return new CharacterTypeFinder().getCharacterType(characterId);
  }
}