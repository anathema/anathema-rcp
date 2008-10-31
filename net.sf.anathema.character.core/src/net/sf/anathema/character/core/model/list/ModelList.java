package net.sf.anathema.character.core.model.list;

import java.util.ArrayList;
import java.util.List;

public class ModelList implements IModelList {

  public List<String> allModelIds = new ArrayList<String>();
  public List<String> allModelListIds = new ArrayList<String>();
  private final IModelListProvider modelListProvider;

  public ModelList(IModelListProvider modelListProvider) {
    this.modelListProvider = modelListProvider;
  }

  public void addModelId(String modelId) {
    allModelIds.add(modelId);
  }

  public void addModelListId(String modelListId) {
    allModelListIds.add(modelListId);
  }

  @Override
  public boolean contains(String modelId) {
    if (allModelIds.contains(modelId)) {
      return true;
    }
    for (String modelListId : allModelListIds) {
      if (modelListProvider.getModelList(modelListId).contains(modelId)) {
        return true;
      }
    }
    return false;
  }
}