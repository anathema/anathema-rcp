package net.sf.anathema.character.core.resource;

import org.eclipse.core.resources.IMarker;

public interface IModelMarker {

  public String getMarkerId();

  public boolean isActive(IMarker[] markers);

  public String getDescription(String modelName, String characterName);
}