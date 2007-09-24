package net.sf.anathema.character.core.character;

import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IModel;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.core.model.ModelIdentifier;

public class ModelContainer implements IModelContainer {

  private final IModelProvider modelProvider;
  private final ICharacterId characterId;

  public ModelContainer(IModelProvider modelProvider, ICharacterId characterId) {
    this.modelProvider = modelProvider;
    this.characterId = characterId;
  }
  
  @Override
  public IModel getModel(String modelId) {
    return modelProvider.getModel(new ModelIdentifier(characterId, modelId));
  }
}