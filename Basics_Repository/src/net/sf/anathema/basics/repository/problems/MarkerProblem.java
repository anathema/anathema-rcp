package net.sf.anathema.basics.repository.problems;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IWorkbenchPage;

public class MarkerProblem implements IProblem {

  public static final String ATTRIB_PATH = "path"; //$NON-NLS-1$
  public static final String ATTRIB_DESCRIPTION = "description"; //$NON-NLS-1$
  public static final String ATTRIB_SOURCE_OPENER = "openerId"; //$NON-NLS-1$
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
  
  @Override
  public void showSource(IWorkbenchPage page) throws CoreException {
    String openerId = marker.getAttribute(ATTRIB_SOURCE_OPENER, null);
    new MarkerSourceOpenerExtensionPoint().open(page, openerId, marker.getResource());
  }
}