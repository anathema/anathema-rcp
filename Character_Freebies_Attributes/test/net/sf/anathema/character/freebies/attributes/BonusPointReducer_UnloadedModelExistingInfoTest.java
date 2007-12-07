package net.sf.anathema.character.freebies.attributes;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.anathema.basics.eclipse.resource.fake.ResourceObjectMother;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.fake.CharacterObjectMother;
import net.sf.anathema.character.core.model.IModelResourceHandler;
import net.sf.anathema.character.freebies.attributes.AttributeFreebiesBonusPointReducer_CalculationTest.DummyCreditManager;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.points.configuration.IPointHandler;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class BonusPointReducer_UnloadedModelExistingInfoTest {

  @Parameters
  public static Collection<Object[]> data() {
    List<Object[]> handlerClasses = new ArrayList<Object[]>();
    handlerClasses.add(new Object[] { AttributeFreebiesBonusPointReducer.class, "attributeFreebies" }); //$NON-NLS-1$
    handlerClasses.add(new Object[] { FavoredAttributeBonusPointReducer.class, "favoredAttributes" }); //$NON-NLS-1$
    return handlerClasses;
  }

  private final ModelIdentifier modelIdentifier;
  private final IPointHandler bonusPointHandler;
  private final IModelResourceHandler resourceHandler;
  private final String handlerType;

  public BonusPointReducer_UnloadedModelExistingInfoTest(
      Class< ? extends IPointHandler> handlerClass,
      String handlerType) throws Exception {
    this.handlerType = handlerType;
    ICharacterId characterId = EasyMock.createMock(ICharacterId.class);
    modelIdentifier = new ModelIdentifier(characterId, "net.sf.anathema.character.attributes.model"); //$NON-NLS-1$
    IModelCollection modelCollection = CharacterObjectMother.createNonLoadingEmptyModelProvider();
    resourceHandler = EasyMock.createMock(IModelResourceHandler.class);
    Constructor< ? extends IPointHandler> constructor = handlerClass.getConstructor(
        IModelCollection.class,
        IModelResourceHandler.class,
        ICreditManager.class);
    bonusPointHandler = constructor.newInstance(modelCollection, resourceHandler, new DummyCreditManager());
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
    IMarker[] markers = new IMarker[] { CharacterObjectMother.createBonusPointMarker(handlerType, 3) };
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