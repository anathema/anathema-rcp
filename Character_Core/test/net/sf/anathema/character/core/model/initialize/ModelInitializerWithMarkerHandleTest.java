package net.sf.anathema.character.core.model.initialize;

import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.basics.eclipse.resource.IMarkerHandle;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.mark.IModelMarker;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class ModelInitializerWithMarkerHandleTest {

  private static final String MY_NICE_MODEL = "myNiceModel"; //$NON-NLS-1$
  private ModelIdentifier modelIdentifier;
  private List<IModelMarker> markers;
  private ModelInitializer modelInitializer;
  private IMarkerHandle markerHandle;

  @Before
  public void createInitializerWithMarkerHandle() throws Exception {
    modelIdentifier = new ModelIdentifier(EasyMock.createMock(ICharacterId.class), MY_NICE_MODEL);
    markers = new ArrayList<IModelMarker>();
    IModelMarkerCollection markerCollection = EasyMock.createNiceMock(IModelMarkerCollection.class);
    EasyMock.expect(markerCollection.getModelMarkers(MY_NICE_MODEL)).andReturn(markers).anyTimes();
    EasyMock.replay(markerCollection);
    markerHandle = EasyMock.createMock(IMarkerHandle.class);
    IContentHandle contentHandler = createMock(IContentHandle.class);
    expect(contentHandler.getAdapter(IMarkerHandle.class)).andReturn(markerHandle).anyTimes();
    replay(contentHandler);
    modelInitializer = new ModelInitializer(null, contentHandler, modelIdentifier, markerCollection);
  }

  @Test
  public void markerIsCalled() throws Exception {
    IModelMarker calledMarker = EasyMock.createMock(IModelMarker.class);
    calledMarker.mark(markerHandle, modelIdentifier);
    EasyMock.replay(calledMarker);
    markers.add(calledMarker);
    modelInitializer.initialize();
    EasyMock.verify(calledMarker);
  }
}