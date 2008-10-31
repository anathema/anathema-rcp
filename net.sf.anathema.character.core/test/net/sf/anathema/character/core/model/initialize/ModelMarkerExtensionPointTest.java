package net.sf.anathema.character.core.model.initialize;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.character.core.model.mark.IModelMarker;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class ModelMarkerExtensionPointTest {

  private static final String MY_NICE_MODEL = "myNiceModel"; //$NON-NLS-1$
  private ModelMarkerExtensionPoint point;

  @Before
  public void name() throws Exception {
    IModelMarker marker = EasyMock.createNiceMock(IModelMarker.class);
    IExtensionElement element = createElement(marker, MY_NICE_MODEL);
    point = new ModelMarkerExtensionPoint(ExtensionObjectMother.createPluginExtension(element));
  }
  
  @Test
  public void returnsMarkersForKnownModelId() throws Exception {
    Iterable<IModelMarker> modelMarkers = point.getModelMarkers(MY_NICE_MODEL);
    assertTrue(modelMarkers.iterator().hasNext());
  }

  @Test
  public void returnsNoMarkersForUnknownModelId() throws Exception {
    Iterable<IModelMarker> modelMarkers = point.getModelMarkers("NobodyKnowsMe"); //$NON-NLS-1$
    assertFalse(modelMarkers.iterator().hasNext());
  }

  private IExtensionElement createElement(IModelMarker marker, String modelId) throws Exception {
    IExtensionElement element = EasyMock.createMock(IExtensionElement.class);
    EasyMock.expect(element.getAttribute("modelId")).andReturn(modelId).anyTimes(); //$NON-NLS-1$
    EasyMock.expect(element.getAttributeAsObject("class", IModelMarker.class)).andReturn(marker).anyTimes(); //$NON-NLS-1$
    EasyMock.replay(element);
    return element;
  }
}