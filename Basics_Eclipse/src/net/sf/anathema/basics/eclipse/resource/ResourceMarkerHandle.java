package net.sf.anathema.basics.eclipse.resource;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

public class ResourceMarkerHandle implements IMarkerHandle {
  
  private final IResource resource;

  public ResourceMarkerHandle(IResource resource) {
    this.resource = resource;
  }

  @Override
  public IMarker createMarker(String type) throws CoreException {
    return resource.createMarker(type);
  }

  @Override
  public IMarker[] findMarkers(String type, boolean includeSubtypes, int depth) throws CoreException {
    return resource.findMarkers(type, includeSubtypes, depth);
  }
}