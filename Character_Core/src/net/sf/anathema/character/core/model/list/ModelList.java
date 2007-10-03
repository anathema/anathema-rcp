package net.sf.anathema.character.core.model.list;

import java.util.ArrayList;
import java.util.List;

public class ModelList implements IModelList {

  public List<String> allModelIds = new ArrayList<String>(); 
  
  public void addModelId(String modelId) {
    allModelIds.add(modelId);
  }

  @Override
  public boolean contains(String modelId) {
    return allModelIds.contains(modelId);
  }
}