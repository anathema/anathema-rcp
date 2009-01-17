package net.sf.anathema.character.freebies.problem;

import net.sf.anathema.basics.repository.problems.MarkerProblem;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

public class ProblemResourceMarker implements IResourceMarker {

  private final IResource resource;
  private final ProblemMarkerTemplate template;

  public ProblemResourceMarker(IResource resource, ProblemMarkerTemplate template) {
    this.resource = resource;
    this.template = template;
  }

  @Override
  public boolean exists() throws CoreException {
    IMarker[] markers = resource.findMarkers(template.type, false, IResource.DEPTH_ZERO);
    return markers.length != 0;
  }

  @Override
  public void create() throws CoreException {
    IMarker marker = resource.createMarker(template.type);
    marker.setAttribute(MarkerProblem.ATTRIB_DESCRIPTION, template.description);
    marker.setAttribute(MarkerProblem.ATTRIB_PATH, template.path);
    marker.setAttribute(MarkerProblem.ATTRIB_SOURCE_OPENER, template.editorOpener);
  }

  @Override
  public void delete() throws CoreException {
    resource.deleteMarkers(template.type, false, IResource.DEPTH_ZERO);
  }
}