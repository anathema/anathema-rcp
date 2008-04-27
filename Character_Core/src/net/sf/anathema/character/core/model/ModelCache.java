package net.sf.anathema.character.core.model;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;

public class ModelCache implements IModelCollection {

  private static final ModelCache instance = new ModelCache();
  private final Map<IModelIdentifier, IModel> modelsByIdentifier = new HashMap<IModelIdentifier, IModel>();
  private final Map<IModel, IModelIdentifier> identifiersByModel = new HashMap<IModel, IModelIdentifier>();
  private final DependenciesHandler dependenciesHandler = new DependenciesHandler(new CharacterTemplateProvider());

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
        loadDependencies(identifier);
      }
    }
    return model;
  }

  private void loadDependencies(IModelIdentifier identifier) {
    for (String neededId : dependenciesHandler.getNeededIds(identifier)) {
      getModel(new ModelIdentifier(identifier.getCharacterId(), neededId));
    }
  }

  public synchronized void revert(IModel item) {
    IModelIdentifier modelIdentifier = identifiersByModel.get(item);
    removeFromCache(item, modelIdentifier);
    getModel(modelIdentifier);
  }

  private void removeFromCache(IModel item, IModelIdentifier modelIdentifier) {
    identifiersByModel.remove(item);
    modelsByIdentifier.remove(modelIdentifier);
  }

  @Override
  public boolean contains(IModelIdentifier identifier) {
    return modelsByIdentifier.containsKey(identifier);
  }

  public void clear() {
    modelsByIdentifier.clear();
    identifiersByModel.clear();
  }
}