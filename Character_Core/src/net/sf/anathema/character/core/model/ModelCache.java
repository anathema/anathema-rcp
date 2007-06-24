package net.sf.anathema.character.core.model;

import java.util.HashMap;
import java.util.Map;

public class ModelCache {

  private static final ModelCache instance = new ModelCache();
  private Map<ModelIdentifier, Object> modelsByIdentifier = new HashMap<ModelIdentifier, Object>();

  public static ModelCache getInstance() {
    return instance;
  }

  public void addModel(ModelIdentifier identifier, Object model) {
    modelsByIdentifier.put(identifier, model);
  }
  
  public Object getModel(ModelIdentifier identifier) {
    return modelsByIdentifier.get(identifier);
  }
}