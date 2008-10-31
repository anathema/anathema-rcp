package net.sf.anathema.basics.repository.problems;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IMarker;
import org.junit.Before;
import org.junit.Test;

public class MarkerProblemTest {

  private MarkerProblem problem;
  private IMarker marker;

  @Before
  public void createProblem() {
    marker = EasyMock.createMock(IMarker.class);
    problem = new MarkerProblem(marker);
  }

  @Test
  public void descriptionIsAskedFromMarker() throws Exception {
    String description = "myNiceDescription"; //$NON-NLS-1$
    EasyMock.expect(marker.getAttribute("description", Messages.MarkerProblem_NoDescription)).andReturn(description); //$NON-NLS-1$
    EasyMock.replay(marker);
    assertEquals(description, problem.getDescription());
  }

  @Test
  public void pathIsAskedFromMarker() throws Exception {
    String description = "myNiceDescription"; //$NON-NLS-1$
    EasyMock.expect(marker.getAttribute("path", Messages.MarkerProblem_NoPath)).andReturn(description); //$NON-NLS-1$
    EasyMock.replay(marker);
    assertEquals(description, problem.getPath());
  }
}