package net.sf.anathema.character.freebies.attributes.mark;

import net.sf.anathema.basics.eclipse.resource.IMarkerHandle;
import net.sf.anathema.character.freebies.attributes.calculation.IAttributeCreditCollection;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.PriorityGroup;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IMarker;
import org.junit.Before;
import org.junit.Test;

public class AttributeResourceMarkerWithCreditTest {

  private static final String MARKER_ID = "so.ein.schicker.marker.ist.schon.da"; //$NON-NLS-1$
  private static final int CREDIT = 2;
  private static final PriorityGroup PRIORITY = PriorityGroup.Primary;
  private DummyChangeableModel model;
  private ITotalDotsSpent dotsSpent;
  private ResourceModelMarker attributeResourceMarker;
  private IMarkerHandle markerHandler;

  @Before
  public void createMarker() throws Exception {
    IAttributeCreditCollection creditCollection = createCreditCollection(PRIORITY, CREDIT);
    dotsSpent = EasyMock.createMock(ITotalDotsSpent.class);
    model = new DummyChangeableModel();
    markerHandler = EasyMock.createMock(IMarkerHandle.class);
    attributeResourceMarker = new ResourceModelMarker(model, markerHandler, new AttributeGroupModelMarker(
        creditCollection,
        dotsSpent,
        PRIORITY,
        MARKER_ID));
  }

  private IAttributeCreditCollection createCreditCollection(PriorityGroup priority, int credit) {
    IAttributeCreditCollection creditCollection = EasyMock.createMock(IAttributeCreditCollection.class);
    EasyMock.expect(creditCollection.getCredit(priority)).andReturn(credit).anyTimes();
    EasyMock.replay(creditCollection);
    return creditCollection;
  }

  @Test
  public void noMarkerManipulationIfHandleDoesNotExist() throws Exception {
    EasyMock.expect(markerHandler.exists()).andReturn(false).anyTimes();
    EasyMock.replay(markerHandler);
    attributeResourceMarker.mark();
    EasyMock.verify(markerHandler);
  }

  @Test
  public void markerDeletedIfCreditIsDepleted() throws Exception {
    EasyMock.expect(markerHandler.exists()).andReturn(true).anyTimes();
    markerHandler.deleteMarkers(MARKER_ID, true, 0);
    EasyMock.expect(dotsSpent.get(PRIORITY)).andReturn(CREDIT).anyTimes();
    EasyMock.replay(markerHandler, dotsSpent);
    attributeResourceMarker.mark();
    EasyMock.verify(markerHandler);
  }

  @Test
  public void markerIsCreatedIfCreditPointsAreStillAvailableAndNoMarkerIsSet() throws Exception {
    EasyMock.expect(markerHandler.exists()).andReturn(true).anyTimes();
    EasyMock.expect(markerHandler.findMarkers(MARKER_ID, true, 0)).andReturn(new IMarker[0]);
    EasyMock.expect(markerHandler.createMarker(MARKER_ID)).andReturn(null);
    EasyMock.expect(dotsSpent.get(PRIORITY)).andReturn(CREDIT - 1).anyTimes();
    EasyMock.replay(markerHandler, dotsSpent);
    attributeResourceMarker.mark();
    EasyMock.verify(markerHandler);
  }

  @Test
  public void noDuplicatedMarkersAreCreated() throws Exception {
    EasyMock.expect(markerHandler.exists()).andReturn(true).anyTimes();
    EasyMock.expect(markerHandler.findMarkers(MARKER_ID, true, 0)).andReturn(
        new IMarker[] { EasyMock.createMock(IMarker.class) });
    EasyMock.expect(dotsSpent.get(PRIORITY)).andReturn(CREDIT - 1).anyTimes();
    EasyMock.replay(markerHandler, dotsSpent);
    attributeResourceMarker.mark();
    EasyMock.verify(markerHandler);
  }
}