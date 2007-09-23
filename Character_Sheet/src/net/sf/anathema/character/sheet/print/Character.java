package net.sf.anathema.character.sheet.print;

import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModel;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.sheet.content.ICharacter;

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
}