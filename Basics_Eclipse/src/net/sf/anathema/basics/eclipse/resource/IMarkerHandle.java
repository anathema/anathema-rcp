package net.sf.anathema.basics.eclipse.resource;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;

public interface IMarkerHandle {

  public IMarker createMarker(String type) throws CoreException;

  public IMarker[] findMarkers(String type, boolean includeSubtypes, int depth) throws CoreException;
}