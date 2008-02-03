package net.sf.anathema.character.core.model.list;

public class NullModelList implements IModelList {

  @Override
  public boolean contains(String modelId) {
    return false;
  }
}