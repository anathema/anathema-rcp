package net.sf.anathema.character.sheet.pdf;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplateProvider;
import net.sf.anathema.character.core.character.ICharacterType;
import net.sf.anathema.character.core.character.ICharacterTypeFinder;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;


public class Character implements ICharacter {

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
  public String getTemplateId() {
    return templateProvider.getTemplate(characterId).getId();
  }

  @Override
  public ICharacterType getCharacterType() {
    return characterTypeFinder.getCharacterType(characterId);
  }
}