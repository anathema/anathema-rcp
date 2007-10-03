package net.sf.anathema.character.core.model;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.IModelIdentifier;


public class ModelCache implements IModelCollection {

  private static final IModelCollection instance = new ModelCache();
  private Map<IModelIdentifier, Object> modelsByIdentifier = new HashMap<IModelIdentifier, Object>();
  
  private ModelCache() {
    // nothing to do
  }

  public static IModelCollection getInstance() {
    return instance;
  }

  public IModel getModel(IModelIdentifier identifier) {
    Object model = modelsByIdentifier.get(identifier);
    if (model == null) {
      model = new ModelExtensionPoint().createModel(identifier);
      if (model != null) {
        modelsByIdentifier.put(identifier, model);
      }
    }
    return (IModel) model;
  }
}