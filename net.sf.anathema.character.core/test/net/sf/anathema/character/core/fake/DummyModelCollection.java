package net.sf.anathema.character.core.fake;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.model.IModelChangeListener;

public class DummyModelCollection implements IModelCollection {

  public final Map<String, IModel> modelsById = new HashMap<String, IModel>();

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

  @Override
  public void addModelChangeListener(IModelChangeListener listener) {
    // nothing to do
  }

  @Override
  public void removeModelChangeListener(IModelChangeListener listener) {
    // nothing to do
  }
}