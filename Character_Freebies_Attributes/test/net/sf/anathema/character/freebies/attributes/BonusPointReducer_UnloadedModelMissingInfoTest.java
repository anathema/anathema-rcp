package net.sf.anathema.character.freebies.attributes;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.anathema.character.attributes.model.AttributeGroupTemplate;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.fake.CharacterObjectMother;
import net.sf.anathema.character.core.model.IModelResourceHandler;
import net.sf.anathema.character.freebies.attributes.AttributeFreebiesBonusPointReducer_CalculationTest.DummyCreditManager;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.points.configuration.IPointHandler;
import net.sf.anathema.character.trait.collection.TraitCollectionFactory;
import net.sf.anathema.character.trait.template.EssenceSensitiveTraitTemplate;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class BonusPointReducer_UnloadedModelMissingInfoTest {

  @Parameters
  public static Collection<Object[]> data() {
    List<Object[]> handlerClasses = new ArrayList<Object[]>();
    handlerClasses.add(new Object[] { AttributeFreebiesBonusPointReducer.class });
    handlerClasses.add(new Object[] { FavoredAttributeBonusPointReducer.class });
    return handlerClasses;
  }

  private final ModelIdentifier modelIdentifier;
  private final IPointHandler bonusPointHandler;
  private final IModelResourceHandler resourceHandler;
  private final IModelCollection modelCollection;

  public BonusPointReducer_UnloadedModelMissingInfoTest(Class< ? extends IPointHandler> handlerClass)
      throws Exception {
    ICharacterId characterId = EasyMock.createMock(ICharacterId.class);
    modelIdentifier = new ModelIdentifier(characterId, "net.sf.anathema.character.attributes.model"); //$NON-NLS-1$
    modelCollection = EasyMock.createNiceMock(IModelCollection.class);
    EasyMock.expect(modelCollection.contains(modelIdentifier)).andStubReturn(false);
    EasyMock.expect(modelCollection.getModel(modelIdentifier)).andStubReturn(
        TraitCollectionFactory.create(new AttributeGroupTemplate().getGroups(), new EssenceSensitiveTraitTemplate()));
    EasyMock.replay(modelCollection);
    resourceHandler = EasyMock.createMock(IModelResourceHandler.class);
    Constructor< ? extends IPointHandler> constructor = handlerClass.getConstructor(
        IModelCollection.class,
        IModelResourceHandler.class,
        ICreditManager.class);
    bonusPointHandler = constructor.newInstance(modelCollection, resourceHandler, new DummyCreditManager());
  }

  @Test
  public void modelIsReloadedIfNoMarkerIsFound() throws Exception {
    IFile file = CharacterObjectMother.createFileWithBonusPointMarkers(new IMarker[0]);
    EasyMock.expect(resourceHandler.getResource(modelIdentifier)).andStubReturn(file);
    EasyMock.replay(resourceHandler);
    assertEquals(0, bonusPointHandler.getPoints(modelIdentifier.getCharacterId()));
    EasyMock.verify(modelCollection);
  }

  @Test
  public void modelIsReloadedIfOnlyNonAttributeHandlerMarkerIsFound() throws Exception {
    IMarker[] markers = new IMarker[] { CharacterObjectMother.createBonusPointMarker("unkown", 3) }; //$NON-NLS-1$
    IFile file = CharacterObjectMother.createFileWithBonusPointMarkers(markers);
    EasyMock.expect(resourceHandler.getResource(modelIdentifier)).andStubReturn(file);
    EasyMock.replay(resourceHandler);
    assertEquals(0, bonusPointHandler.getPoints(modelIdentifier.getCharacterId()));
    EasyMock.verify(modelCollection);
  }
}