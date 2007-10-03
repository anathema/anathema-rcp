package net.sf.anathema.character.core.model.mark;

import net.sf.anathema.basics.eclipse.resource.IMarkerHandle;
import net.sf.anathema.character.core.character.IModelIdentifier;

import org.eclipse.core.runtime.IExecutableExtension;

public interface IModelMarker extends IExecutableExtension {

  public void mark(IMarkerHandle markerHandle, IModelIdentifier modelIdentifier);
}