package net.sf.anathema.character.core.problem;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

public class UneditedModelMarker {

  public static final String MARKER_TYPE = "net.sf.anathema.character.uneditedmodel.marker"; //$NON-NLS-1$

  private final IResource resource;

  public UneditedModelMarker(IResource resource) {
    this.resource = resource;
  }

  private void create() throws CoreException {
    IMarker marker = resource.createMarker(MARKER_TYPE);
  }

  private void delete() throws CoreException {
    resource.deleteMarkers(MARKER_TYPE, false, IResource.DEPTH_ZERO);
  }

  public void update() {
    try {
      IMarker[] markers = resource.findMarkers(MARKER_TYPE, false, IResource.DEPTH_ZERO);
      if (markers == null || markers.length == 0) {
        create();
      }
      else {
        delete();
      }
    }
    catch (CoreException e) {
      // TODO Fehlermeldung
      e.printStackTrace();
    }
  }
}