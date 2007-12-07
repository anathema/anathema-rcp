package net.sf.anathema.character.attributes.points;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.fake.CharacterObjectMother;
import net.sf.anathema.character.core.model.IModelResourceHandler;
import net.sf.anathema.character.trait.collection.TraitCollection;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.junit.Before;
import org.junit.Test;

public class AttributeBonusPointHandler_UnloadedModelExistingInfoTest {

  private ModelIdentifier modelIdentifier;
  private AttributeBonusPointHandler bonusPointHandler;
  private IModelResourceHandler resourceHandler;
  private IModelCollection modelCollection;

  @Before
  public void createHandler() throws Exception {
    ICharacterId characterId = EasyMock.createMock(ICharacterId.class);
    modelIdentifier = new ModelIdentifier(characterId, "net.sf.anathema.character.attributes.model"); //$NON-NLS-1$
    modelCollection = EasyMock.createNiceMock(IModelCollection.class);
    EasyMock.expect(modelCollection.contains(modelIdentifier)).andStubReturn(false);
    EasyMock.expect(modelCollection.getModel(modelIdentifier)).andReturn(new TraitCollection());
    EasyMock.replay(modelCollection);
    resourceHandler = EasyMock.createMock(IModelResourceHandler.class);
    bonusPointHandler = new AttributeBonusPointHandler(modelCollection, resourceHandler);
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
    IMarker[] markers = new IMarker[] { CharacterObjectMother.createBonusPointMarker("attributeFreebies", 3) }; //$NON-NLS-1$
    IFile file = CharacterObjectMother.createFileWithBonusPointMarkers(markers);
    EasyMock.expect(resourceHandler.getResource(modelIdentifier)).andStubReturn(file);
    EasyMock.replay(resourceHandler);
    assertEquals(0, bonusPointHandler.getPoints(modelIdentifier.getCharacterId()));
    EasyMock.verify(modelCollection);
  }
}