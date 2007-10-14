package net.sf.anathema.basics.repository.problems;

import org.eclipse.core.resources.IMarker;

public class MarkerProblem implements IProblem {

  public static final String ATTRIB_PATH = "path"; //$NON-NLS-1$
  public static final String ATTRIB_DESCRIPTION = "description"; //$NON-NLS-1$
  private final IMarker marker;

  public MarkerProblem(IMarker marker) {
    this.marker = marker;
  }

  @Override
  public String getDescription() {
    return marker.getAttribute(ATTRIB_DESCRIPTION, Messages.MarkerProblem_NoDescription);
  }

  @Override
  public String getPath() {
    return marker.getAttribute(ATTRIB_PATH, Messages.MarkerProblem_NoPath);
  }
}