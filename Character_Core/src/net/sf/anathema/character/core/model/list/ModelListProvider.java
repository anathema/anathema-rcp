package net.sf.anathema.character.core.model.list;

import java.util.HashMap;
import java.util.Map;

public class ModelListProvider implements IModelListProvider {

  private final Map<String, IModelList> modelListsById = new HashMap<String, IModelList>();

  /**
   * By this method, each template is given a description as well as attributes and all further models registered as
   * part of the model list.
   */
  public ModelListProvider() {
    ModelList modelList = new ModelList();
    modelList.addModelId("net.sf.anathema.character.description.model"); //$NON-NLS-1$
    modelList.addModelId("net.sf.anathema.character.attributes.model"); //$NON-NLS-1$
    modelList.addModelId("net.sf.anathema.character.caste.model"); //$NON-NLS-1$
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