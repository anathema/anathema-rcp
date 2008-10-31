package net.sf.anathema.character.core.model.initialize;

import net.sf.anathema.character.core.model.mark.IModelMarker;

public interface IModelMarkerCollection {

  public Iterable<IModelMarker> getModelMarkers(String modelId);
}