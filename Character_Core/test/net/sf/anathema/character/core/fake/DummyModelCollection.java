package net.sf.anathema.character.core.fake;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.IModelIdentifier;

public class DummyModelCollection implements IModelCollection {

  private Map<String, IModel> modelsById = new HashMap<String, IModel>();
  
  public void addModel(String modelId, IModel model) {
    modelsById.put(modelId, model);
  }
  
  @Override
  public IModel getModel(IModelIdentifier identifier) {
    return modelsById.get(identifier.getModelId());
  }

  @Override
  public boolean contains(IModelIdentifier identifier) {
    return modelsById.containsKey(identifier.getModelId());
  }
}