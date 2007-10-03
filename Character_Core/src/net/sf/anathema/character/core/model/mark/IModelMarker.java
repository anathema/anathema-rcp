package net.sf.anathema.character.core.model.mark;

import net.sf.anathema.basics.eclipse.resource.IMarkerHandle;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.lib.ui.IDisposable;

public interface IModelMarker extends IDisposable {

  public void mark(IMarkerHandle markerHandler, IModelIdentifier modelIdentifier);
}