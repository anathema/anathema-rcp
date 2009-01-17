package net.sf.anathema.character.freebies.problem;

import net.sf.anathema.basics.repository.problems.MarkerProblem;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

public class ProblemResourceMarker implements IResourceMarker {

  private final IResource resource;
  private final ProblemMarkerDto dto;

  public ProblemResourceMarker(IResource resource, ProblemMarkerDto dto) {
    this.resource = resource;
    this.dto = dto;
  }

  @Override
  public boolean exists() throws CoreException {
    IMarker[] markers = resource.findMarkers(dto.type, false, IResource.DEPTH_ZERO);
    return markers.length != 0;
  }

  @Override
  public void create() throws CoreException {
    IMarker marker = resource.createMarker(dto.type);
    marker.setAttribute(MarkerProblem.ATTRIB_DESCRIPTION, dto.description);
    marker.setAttribute(MarkerProblem.ATTRIB_PATH, dto.path);
    marker.setAttribute(MarkerProblem.ATTRIB_SOURCE_OPENER, dto.editorOpener);
  }

  @Override
  public void delete() throws CoreException {
    resource.deleteMarkers(dto.type, false, IResource.DEPTH_ZERO);
  }
}