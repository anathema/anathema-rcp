package net.sf.anathema.character.core.model;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.model.initialize.IModelInitializer;


public class ModelCache implements IModelCollection {

  private static final IModelCollection instance = new ModelCache();
  private Map<IModelIdentifier, IModel> modelsByIdentifier = new HashMap<IModelIdentifier, IModel>();
  
  private ModelCache() {
    // nothing to do
  }

  public static IModelCollection getInstance() {
    return instance;
  }

  public IModel getModel(IModelIdentifier identifier) {
    IModel model = modelsByIdentifier.get(identifier);
    if (model == null) {
      IModelInitializer initializer = new ModelExtensionPoint().createModel(identifier); 
      model = initializer.getModel();
      if (model != null) {
        modelsByIdentifier.put(identifier, model);
        initializer.createMarkers();
      }
    }
    return model;
  }
}