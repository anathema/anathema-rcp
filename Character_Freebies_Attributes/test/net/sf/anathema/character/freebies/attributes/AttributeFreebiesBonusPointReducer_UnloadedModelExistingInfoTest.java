package net.sf.anathema.character.freebies.attributes;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.basics.eclipse.resource.fake.ResourceObjectMother;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.fake.CharacterObjectMother;
import net.sf.anathema.character.core.model.IModelResourceHandler;
import net.sf.anathema.character.freebies.attributes.AttributeFreebiesBonusPointReducer_CalculationTest.DummyCreditManager;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.junit.Before;
import org.junit.Test;

public class AttributeFreebiesBonusPointReducer_UnloadedModelExistingInfoTest {

  private ModelIdentifier modelIdentifier;
  private AttributeFreebiesBonusPointReducer bonusPointHandler;
  private IModelResourceHandler resourceHandler;

  @Before
  public void createHandler() throws Exception {
    ICharacterId characterId = EasyMock.createMock(ICharacterId.class);
    modelIdentifier = new ModelIdentifier(characterId, "net.sf.anathema.character.attributes.model"); //$NON-NLS-1$
    IModelCollection modelCollection = CharacterObjectMother.createNonLoadingEmptyModelProvider();
    resourceHandler = EasyMock.createMock(IModelResourceHandler.class);
    bonusPointHandler = new AttributeFreebiesBonusPointReducer(
        modelCollection,
        resourceHandler,
        new DummyCreditManager());
  }

  @Test
  public void zeroBonusPointsWithoutLoadingModelForNonExistingResource() throws Exception {
    IFile nonExistingFile = ResourceObjectMother.createNonExistingFile();
    EasyMock.expect(resourceHandler.getResource(modelIdentifier)).andStubReturn(nonExistingFile);
    EasyMock.replay(resourceHandler);
    assertEquals(0, bonusPointHandler.getPoints(modelIdentifier.getCharacterId()));
    EasyMock.verify(resourceHandler);
  }

  @Test
  public void bonusPointsFromMarkerWithoutLoadingModelForExistingResource() throws Exception {
    IMarker[] markers = new IMarker[] { CharacterObjectMother.createBonusPointMarker("attributeFreebies", 3) }; //$NON-NLS-1$
    IFile file = EasyMock.createNiceMock(IFile.class);
    EasyMock.expect(file.exists()).andStubReturn(true);
    EasyMock.expect(file.findMarkers("net.sf.anathema.markers.bonuspoints", false, IResource.DEPTH_ZERO)).andReturn( //$NON-NLS-1$
        markers);
    EasyMock.replay(file);
    EasyMock.expect(resourceHandler.getResource(modelIdentifier)).andStubReturn(file);
    EasyMock.replay(resourceHandler);
    assertEquals(3, bonusPointHandler.getPoints(modelIdentifier.getCharacterId()));
    EasyMock.verify(resourceHandler);
  }
}