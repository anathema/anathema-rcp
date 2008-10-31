package net.sf.anathema.character.core.model.initialize;

import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.ModelInitializer;
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
    modelIdentifier = new ModelIdentifier(createMock(ICharacterId.class), MY_NICE_MODEL);
    markers = new ArrayList<IModelMarker>();
    IModelMarkerCollection markerCollection = createNiceMock(IModelMarkerCollection.class);
    expect(markerCollection.getModelMarkers(MY_NICE_MODEL)).andReturn(markers).anyTimes();
    replay(markerCollection);
    modelInitializer = new ModelInitializer(
        createNiceMock(IModel.class),
        createNiceMock(IContentHandle.class),
        modelIdentifier,
        markerCollection);
  }

  @Test
  public void noMarkerIsCalled() throws Exception {
    IModelMarker uncalledMarker = EasyMock.createMock(IModelMarker.class);
    EasyMock.replay(uncalledMarker);
    markers.add(uncalledMarker);
    modelInitializer.initialize();
    EasyMock.verify(uncalledMarker);
  }
}