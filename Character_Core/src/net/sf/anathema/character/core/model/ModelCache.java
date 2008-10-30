package net.sf.anathema.character.core.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.change.AggregatedModelChangeProcessor;
import net.sf.anathema.character.core.model.change.IModelChangeProcessor;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.lib.ui.ChangeableModelDisposable;
import net.sf.anathema.lib.ui.IDisposable;

public class ModelCache implements IModelCache {

  public static final class ModelChangeDelegate implements IChangeListener {
    private final IModel newModel;
    private final IModelIdentifier identifier;
    private final IModelChangeProcessor changeProcessor;

    public ModelChangeDelegate(IModelChangeProcessor changeProcessor, IModel model, IModelIdentifier identifier) {
      this.changeProcessor = changeProcessor;
      this.newModel = model;
      this.identifier = identifier;
      model.addChangeListener(this);
    }

    @Override
    public void stateChanged() {
      changeProcessor.modelChanged(newModel, identifier);
    }
  }

  private static final ModelCache instance = new ModelCache();
  private final Map<IModelIdentifier, IModel> modelsByIdentifier = new HashMap<IModelIdentifier, IModel>();
  private final Map<IModel, IModelIdentifier> identifiersByModel = new HashMap<IModel, IModelIdentifier>();
  private final Map<IModelIdentifier, IDisposable> disposableByIdentifer = new HashMap<IModelIdentifier, IDisposable>();
  private final DependenciesHandler dependenciesHandler = new DependenciesHandler(new CharacterTemplateProvider());
  private final IModelChangeProcessor changeProcessor = new AggregatedModelChangeProcessor();

  private ModelCache() {
    // nothing to do
  }

  public static ModelCache getInstance() {
    return instance;
  }

  public synchronized IModel getModel(final IModelIdentifier identifier) {
    IModel model = modelsByIdentifier.get(identifier);
    if (model == null) {
      IModelInitializer initializer = new ModelExtensionPoint().createModel(identifier);
      final IModel newModel = initializer.getModel();
      model = newModel;
      if (model != null) {
        //IChangeListener changeListener = new ModelChangeDelegate(changeProcessor, newModel, identifier);
        //disposableByIdentifer.put(identifier, new ChangeableModelDisposable(newModel, changeListener));
        storeModel(identifier, model);
        initializer.initialize();
        loadDependencies(identifier, model);
      }
    }
    return model;
  }

  protected void storeModel(IModelIdentifier identifier, IModel model) {
    modelsByIdentifier.put(identifier, model);
    identifiersByModel.put(model, identifier);
  }

  private void loadDependencies(IModelIdentifier identifier, final IModel dependentModel) {
    for (String neededId : dependenciesHandler.getNeededIds(identifier)) {
      IModel neededModel = getModel(new ModelIdentifier(identifier.getCharacterId(), neededId));
      neededModel.addChangeListener(new IChangeListener() {
        @Override
        public void stateChanged() {
          dependentModel.updateToDependencies();
        }
      });
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

  @Override
  public void clearAllModels(ICharacterId id) {
    HashMap<IModelIdentifier, IModel> clone = new HashMap<IModelIdentifier, IModel>(modelsByIdentifier);
    for (Entry<IModelIdentifier, IModel> entry : clone.entrySet()) {
      if (entry.getKey().getCharacterId().equals(id)) {
        removeFromCache(entry.getValue(), entry.getKey());
      }
    }
  }
}