package net.sf.anathema.character.core.modellist;

import java.util.HashMap;
import java.util.Map;

public class ModelListProvider implements IModelListProvider {

  private Map<String, IModelList> modelListsById = new HashMap<String, IModelList>();
  
  public ModelListProvider() {
    ModelList modelList = new ModelList();
    modelList.addModelId("net.sf.anathema.character.description.model"); //$NON-NLS-1$
    modelList.addModelId("net.sf.anathema.character.attributes.model"); //$NON-NLS-1$
    addModelList("net.sf.anathema.character.basic.modelList", modelList); //$NON-NLS-1$
  }

  public void addModelList(String modelListId, IModelList modelList) {
    modelListsById.put(modelListId, modelList);
  }
  
  @Override
  public IModelList getModelList(String modelListId) {
    IModelList modelList = modelListsById.get(modelListId);
    if (modelList == null) {
      return new ModelList();
    }
    return modelList;
  }
}