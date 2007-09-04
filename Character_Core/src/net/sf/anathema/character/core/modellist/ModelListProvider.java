package net.sf.anathema.character.core.modellist;

import java.util.HashMap;
import java.util.Map;

public class ModelListProvider implements IModelListProvider {

  private Map<String, IModelList> modelListsById = new HashMap<String, IModelList>();
  
  public ModelListProvider() {
    ModelList modelList = new ModelList();
    modelList.addModelId("net.sf.anathema.character.description.model"); //$NON-NLS-1$
    modelList.addModelId("net.sf.anathema.character.attributes.model"); //$NON-NLS-1$
    modelListsById.put("net.sf.anathema.character.basic.modelList", modelList); //$NON-NLS-1$
  }
  
  @Override
  public IModelList getModelList(String modelListId) {
    return modelListsById.get(modelListId);
  }
}