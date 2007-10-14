package net.sf.anathema.basics.repository.problems;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.junit.Before;
import org.junit.Test;

public class ProblemsContentProviderContainerTest {

  private ProblemsContentProvider provider;
  private IResource resource;
  private IContainer container;

  @Before
  public void createProvider() {
    provider = new ProblemsContentProvider();
  }

  @Before
  public void createInput() throws CoreException {
    container = EasyMock.createMock(IContainer.class);
    resource = EasyMock.createMock(IResource.class);
    EasyMock.expect(container.members()).andReturn(new IResource[] { container });
    EasyMock.expect(container.members()).andReturn(new IResource[] { resource });
    EasyMock.replay(container);
  }

  @Test
  public void noProblemsFoundIfNoMarkersSet() throws Exception {
    IMarker[] markers = new IMarker[0];
    defineMarkers(markers);
    IProblem[] problems = provider.getElements(container);
    assertEquals(0, problems.length);
  }

  @Test
  public void problemIsCreatedForEachMarkerFound() throws Exception {
    IMarker[] markers = new IMarker[3];
    defineMarkers(markers);
    IProblem[] problems = provider.getElements(container);
    assertEquals(markers.length, problems.length);
  }

  private void defineMarkers(IMarker[] markers) throws CoreException {
    EasyMock.expect(resource.findMarkers("net.sf.anathema.markers.view.element", true, 1)).andReturn(markers); //$NON-NLS-1$
    EasyMock.replay(resource);
  }
}