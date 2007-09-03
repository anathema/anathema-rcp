package net.sf.anathema.character.core.template;

import java.util.ArrayList;
import java.util.List;

public class CharacterTemplate implements ICharacterTemplate {
  
  private final String id;
  private final List<String> modelIds = new ArrayList<String>();

  public CharacterTemplate(String id) {
    this.id = id;
  }
  
  public String getId() {
    return id;
  }
  
  public void addModelId(String modelId) {
    modelIds.add(modelId);
  }

  @Override
  public boolean supportsModel(String modelId) {
    return modelIds.contains(modelId);
  }
}