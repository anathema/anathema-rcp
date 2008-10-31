package net.sf.anathema.character.core.model;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.core.character.ModelIdentifier;

public class ModelContainer implements IModelContainer {

  private final IModelCollection modelProvider;
  private final ICharacterId characterId;

  public ModelContainer(IModelCollection modelProvider, ICharacterId characterId) {
    this.modelProvider = modelProvider;
    this.characterId = characterId;
  }
  
  @Override
  public IModel getModel(String modelId) {
    return modelProvider.getModel(new ModelIdentifier(characterId, modelId));
  }
}