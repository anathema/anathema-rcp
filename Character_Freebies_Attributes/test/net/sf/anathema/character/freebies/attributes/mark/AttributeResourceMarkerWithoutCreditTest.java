package net.sf.anathema.character.freebies.attributes.mark;

import net.sf.anathema.basics.eclipse.resource.IMarkerHandle;
import net.sf.anathema.character.freebies.attributes.calculation.IAttributeCreditCollection;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.Priority;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class AttributeResourceMarkerWithoutCreditTest {

  private static final String MARKER_ID = "Scheiﬂ.id"; //$NON-NLS-1$
  private DummyChangeableModel model;
  private ITotalDotsSpent dotsSpent;
  private ResourceModelMarker attributeResourceMarker;
  private IMarkerHandle markerHandler;

  @Before
  public void createMarker() throws Exception {
    Priority priority = Priority.Primary;
    IAttributeCreditCollection creditCollection = createEmptyCreditCollection(priority);
    dotsSpent = EasyMock.createMock(ITotalDotsSpent.class);
    model = new DummyChangeableModel();
    markerHandler = EasyMock.createMock(IMarkerHandle.class);
    attributeResourceMarker = new ResourceModelMarker(model, markerHandler, new AttributeGroupModelMarker(
        creditCollection,
        dotsSpent,
        priority,
        MARKER_ID));
  }

  private IAttributeCreditCollection createEmptyCreditCollection(Priority priority) {
    IAttributeCreditCollection creditCollection = EasyMock.createMock(IAttributeCreditCollection.class);
    EasyMock.expect(creditCollection.getCredit(priority)).andReturn(0).anyTimes();
    EasyMock.replay(creditCollection);
    return creditCollection;
  }

  @Test
  public void noMarkerCreated() throws Exception {
    EasyMock.expect(markerHandler.exists()).andReturn(true).anyTimes();
    markerHandler.deleteMarkers(MARKER_ID, true, 0);
    EasyMock.replay(markerHandler);
    attributeResourceMarker.mark();
    EasyMock.verify(markerHandler);
  }
}