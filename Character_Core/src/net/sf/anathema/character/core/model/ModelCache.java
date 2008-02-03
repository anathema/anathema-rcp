package net.sf.anathema.character.core.model;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.model.initialize.IModelInitializer;

public class ModelCache implements IModelCollection {

  private static final ModelCache instance = new ModelCache();
  private Map<IModelIdentifier, IModel> modelsByIdentifier = new HashMap<IModelIdentifier, IModel>();
  private Map<IModel, IModelIdentifier> identifiersByModel = new HashMap<IModel, IModelIdentifier>();

  private ModelCache() {
    // nothing to do
  }

  public static ModelCache getInstance() {
    return instance;
  }

  public synchronized IModel getModel(IModelIdentifier identifier) {
    IModel model = modelsByIdentifier.get(identifier);
    if (model == null) {
      IModelInitializer initializer = new ModelExtensionPoint().createModel(identifier);
      model = initializer.getModel();
      if (model != null) {
        modelsByIdentifier.put(identifier, model);
        identifiersByModel.put(model, identifier);
        initializer.initialize();
      }
    }
    return model;
  }

  public synchronized void revert(IModel item) {
    IModelIdentifier modelIdentifier = identifiersByModel.get(item);
    identifiersByModel.remove(item);
    modelsByIdentifier.remove(modelIdentifier);
    getModel(modelIdentifier);
  }

  @Override
  public boolean contains(IModelIdentifier identifier) {
    return modelsByIdentifier.containsKey(identifier);
  }
}