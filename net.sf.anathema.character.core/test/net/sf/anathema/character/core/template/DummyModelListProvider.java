package net.sf.anathema.character.core.template;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.core.model.list.IModelList;
import net.sf.anathema.character.core.model.list.IModelListProvider;

public class DummyModelListProvider implements IModelListProvider {
  private final Map<String, IModelList> modelListsById = new HashMap<String, IModelList>();

  @Override
  public IModelList getModelList(String modelListName) {
    return modelListsById.get(modelListName);
  }

  public void addModelList(String listId, IModelList modelList) {
    modelListsById.put(listId, modelList);
  }
}