package net.sf.anathema.character.core.model.initialize;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.mark.IModelMarker;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class ModelInitializerWithoutMarkerHandleTest {

  private static final String MY_NICE_MODEL = "myNiceModel"; //$NON-NLS-1$
  private ModelIdentifier modelIdentifier;
  private List<IModelMarker> markers;
  private ModelInitializer modelInitializer;

  @Before
  public void createInitializer() throws Exception {
    modelIdentifier = new ModelIdentifier(EasyMock.createMock(ICharacterId.class), MY_NICE_MODEL);
    markers = new ArrayList<IModelMarker>();
    IModelMarkerCollection markerCollection = EasyMock.createNiceMock(IModelMarkerCollection.class);
    EasyMock.expect(markerCollection.getModelMarkers(MY_NICE_MODEL)).andReturn(markers).anyTimes();
    EasyMock.replay(markerCollection);
    modelInitializer = new ModelInitializer(null, null, modelIdentifier, markerCollection);
  }
  
  @Test
  public void noMarkerIsCalled() throws Exception {
    IModelMarker uncalledMarker = EasyMock.createMock(IModelMarker.class);
    EasyMock.replay(uncalledMarker);
    markers.add(uncalledMarker);
    modelInitializer.createMarkers();
    EasyMock.verify(uncalledMarker);
  }
}