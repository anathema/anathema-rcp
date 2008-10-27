package net.sf.anathema.character.freebies.attributes.mark;

import net.sf.anathema.basics.eclipse.resource.IMarkerHandle;
import net.sf.anathema.character.freebies.attributes.calculation.IAttributeCreditCollection;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.Priority;
import net.sf.anathema.character.freebies.mark.ResourceModelMarker;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class AttributeResourceMarkerWithCreditTest {

  private static final String MARKER_ID = "so.ein.schicker.marker.ist.schon.da"; //$NON-NLS-1$
  private static final int CREDIT = 2;
  private static final Priority PRIORITY = Priority.Primary;
  private DummyChangeableModel model;
  private ITotalDotsSpent dotsSpent;
  private ResourceModelMarker attributeResourceMarker;
  private IMarkerHandle markerHandle;

  @Before
  public void createMarker() throws Exception {
    IAttributeCreditCollection creditCollection = createCreditCollection(PRIORITY, CREDIT);
    dotsSpent = EasyMock.createMock(ITotalDotsSpent.class);
    model = new DummyChangeableModel();
    markerHandle = EasyMock.createMock(IMarkerHandle.class);
    attributeResourceMarker = new ResourceModelMarker(model, markerHandle, new AttributeGroupModelMarker(
        creditCollection,
        dotsSpent,
        PRIORITY,
        MARKER_ID));
  }

  private IAttributeCreditCollection createCreditCollection(Priority priority, int credit) {
    IAttributeCreditCollection creditCollection = EasyMock.createMock(IAttributeCreditCollection.class);
    EasyMock.expect(creditCollection.getCredit(priority)).andReturn(credit).anyTimes();
    EasyMock.replay(creditCollection);
    return creditCollection;
  }

  @Test
  public void noMarkerManipulationIfHandleDoesNotExist() throws Exception {
    EasyMock.expect(markerHandle.exists()).andReturn(false).anyTimes();
    EasyMock.replay(markerHandle);
    attributeResourceMarker.mark();
    EasyMock.verify(markerHandle);
  }
}