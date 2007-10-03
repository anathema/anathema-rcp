package net.sf.anathema.character.core.model.initialize;

import net.sf.anathema.character.core.character.IModel;

public interface IModelInitializer {

  public IModel getModel();

  public void createMarkers();
}