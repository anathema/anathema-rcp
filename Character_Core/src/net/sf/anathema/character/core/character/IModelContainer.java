package net.sf.anathema.character.core.character;

import net.sf.anathema.character.core.model.IModel;


public interface IModelContainer {

  public IModel getModel(String modelId);

}