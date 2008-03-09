package net.sf.anathema.character.freebies.attributes;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.basics.eclipse.resource.fake.ResourceObjectMother;
import net.sf.anathema.character.attributes.model.AttributeGroupTemplate;
import net.sf.anathema.character.attributes.model.DummyTemplateFactory;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.fake.CharacterObjectMother;
import net.sf.anathema.character.core.model.IModelResourceHandler;
import net.sf.anathema.character.freebies.attributes.AttributeFreebiesBonusPointReducer_CalculationTest.DummyCreditManager;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.points.configuration.IPointHandler;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.collection.TraitCollectionFactory;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class BonusPointReducer_LoadedModelTest {

  @Parameters
  public static Collection<Object[]> data() {
    List<Object[]> handlerClasses = new ArrayList<Object[]>();
    handlerClasses.add(new Object[] { AttributeFreebiesBonusPointReducer.class, "attributeFreebies" }); //$NON-NLS-1$
    handlerClasses.add(new Object[] { FavoredAttributeBonusPointReducer.class, "favoredAttributes" }); //$NON-NLS-1$
    return handlerClasses;
  }

  private ModelIdentifier modelIdentifier;
  private IPointHandler bonusPointHandler;
  private IModelResourceHandler resourceHandler;
  private final String handlerType;
  private final Class< ? extends IPointHandler> handlerClass;

  public BonusPointReducer_LoadedModelTest(Class< ? extends IPointHandler> handlerClass, String handlerType)
      throws Exception {
    this.handlerClass = handlerClass;
    this.handlerType = handlerType;
  }

  @Before
  public void createHandler() throws Exception {
    ICharacterId characterId = EasyMock.createMock(ICharacterId.class);
    modelIdentifier = new ModelIdentifier(characterId, "net.sf.anathema.character.attributes.model"); //$NON-NLS-1$
    ITraitCollectionModel attributes = TraitCollectionFactory.create(
        new AttributeGroupTemplate().getGroups(),
        new DummyTemplateFactory());
    IModelCollection modelCollection = CharacterObjectMother.createModelProvider(modelIdentifier, attributes);
    resourceHandler = EasyMock.createMock(IModelResourceHandler.class);
    Constructor< ? extends IPointHandler> constructor = handlerClass.getConstructor(
        IModelCollection.class,
        IModelResourceHandler.class,
        ICreditManager.class);
    bonusPointHandler = constructor.newInstance(modelCollection, resourceHandler, new DummyCreditManager());
  }

  @Test
  public void modelAlreadyLoadedIsUsedForCalculation() throws Exception {
    IResource resource = ResourceObjectMother.createExistingResource();
    EasyMock.expect(resourceHandler.getResource(modelIdentifier)).andStubReturn(resource);
    EasyMock.replay(resourceHandler);
    assertEquals(0, bonusPointHandler.getPoints(modelIdentifier.getCharacterId()));
  }

  @Test
  public void markerIsSetWithCalculation() throws Exception {
    Map<String, Object> markerAttributes = new HashMap<String, Object>();
    markerAttributes.put("handlerType", handlerType); //$NON-NLS-1$
    markerAttributes.put("bonusPoints", 0); //$NON-NLS-1$
    IMarker marker = ResourceObjectMother.createEditedMarker(markerAttributes);
    IFile file = ResourceObjectMother.createFileWithCreatedMarker("net.sf.anathema.markers.bonuspoints", marker); //$NON-NLS-1$
    EasyMock.expect(resourceHandler.getResource(modelIdentifier)).andStubReturn(file);
    EasyMock.replay(resourceHandler);
    bonusPointHandler.getPoints(modelIdentifier.getCharacterId());
    EasyMock.verify(marker);
  }

  @Test
  public void noMarkerUpdateWithCalculationIfDataIsEqual() throws Exception {
    IMarker[] markers = new IMarker[] { CharacterObjectMother.createBonusPointMarker(handlerType, 0) };
    IFile resource = EasyMock.createMock(IFile.class);
    EasyMock.expect(resource.exists()).andStubReturn(true);
    EasyMock.expect(resource.findMarkers("net.sf.anathema.markers.bonuspoints", false, 0)).andReturn(markers); //$NON-NLS-1$
    EasyMock.replay(resource);
    EasyMock.expect(resourceHandler.getResource(modelIdentifier)).andStubReturn(resource);
    EasyMock.replay(resourceHandler);
    bonusPointHandler.getPoints(modelIdentifier.getCharacterId());
    EasyMock.verify(resource);
  }
}