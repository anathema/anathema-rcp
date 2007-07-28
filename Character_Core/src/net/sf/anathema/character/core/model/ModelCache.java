package net.sf.anathema.character.core.model;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.core.model.internal.ModelExtensionPoint;

public class ModelCache implements IModelProvider {

  private static final IModelProvider instance = new ModelCache();
  private Map<IModelIdentifier, Object> modelsByIdentifier = new HashMap<IModelIdentifier, Object>();

  public static IModelProvider getInstance() {
    return instance;
  }

  public void addModel(IModelIdentifier identifier, Object model) {
    modelsByIdentifier.put(identifier, model);
  }

  public Object getModel(IModelIdentifier identifier) {
    Object model = modelsByIdentifier.get(identifier);
    if (model == null) {
      model = new ModelExtensionPoint().createModel(identifier);
      if (model != null) {
        addModel(identifier, model);
      }
    }
    return model;
  }
}