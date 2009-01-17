package net.sf.anathema.character.freebies.problem;

import static org.easymock.EasyMock.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.junit.Before;
import org.junit.Test;

public class ProblemResourceMarker_Test {

  private static final String TYPE = "marker.type"; //$NON-NLS-1$
  private IResource resource;
  private ProblemMarkerDto dto;
  private ProblemResourceMarker resourceMarker;

  @Before
  public void createResourceMarker() {
    resource = createMock(IResource.class);
    dto = new ProblemMarkerDto();
    dto.type = TYPE;
    resourceMarker = new ProblemResourceMarker(resource, dto);
  }

  @Test
  public void doesNotExistIfNoMarkerOfTypeIsFound() throws Exception {
    expect(resource.findMarkers(TYPE, false, IResource.DEPTH_ZERO)).andReturn(new IMarker[0]);
    replay(resource);
    assertThat(resourceMarker.exists(), is(false));
  }

  @Test
  public void existsIfAnyMarkerOfTypeIsFound() throws Exception {
    expect(resource.findMarkers(TYPE, false, IResource.DEPTH_ZERO)).andReturn(new IMarker[1]);
    replay(resource);
    assertThat(resourceMarker.exists(), is(true));
  }

  @Test
  public void deletesMarkerOfTypeOnResourceOnDelete() throws Exception {
    resource.deleteMarkers(TYPE, false, IResource.DEPTH_ZERO);
    replay(resource);
    resourceMarker.delete();
    verify(resource);
  }

  @Test
  public void createsMarkerOfTypeOnResourceOnCreate() throws Exception {
    expect(resource.createMarker(TYPE)).andReturn(EasyMockHelper.createNiceMockAndReplay(IMarker.class));
    replay(resource);
    resourceMarker.create();
    verify(resource);
  }
}