package net.sf.anathema.character.core.template;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.core.modellist.IModelList;

public class CharacterTemplate implements ICharacterTemplate {
  
  private final String id;
  private final List<IModelList> allModelLists = new ArrayList<IModelList>();
  private final String untiteledName;

  public CharacterTemplate(String id, String untiteledName) {
    this.id = id;
    this.untiteledName = untiteledName;
  }
  
  @Override
  public String getId() {
    return id;
  }
  
  @Override
  public String getUnnamedLabel() {
    return untiteledName;
  }
  
  public void addModelList(IModelList modelList) {
    Ensure.ensureArgumentNotNull(modelList);
    allModelLists.add(modelList);
  }

  @Override
  public boolean supportsModel(String modelId) {
    for (IModelList modelList : allModelLists) {
      boolean contains = modelList.contains(modelId);
      if (contains) {
        return true;
      }
    }
    return false;
  }
}