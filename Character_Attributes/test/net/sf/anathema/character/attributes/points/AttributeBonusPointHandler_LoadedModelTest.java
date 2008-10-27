package net.sf.anathema.character.attributes.points;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.resource.fake.ResourceObjectMother;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.fake.CharacterObjectMother;
import net.sf.anathema.character.core.model.IModelResourceHandler;
import net.sf.anathema.character.trait.fake.DummyTraitCollection;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.junit.Before;
import org.junit.Test;

public class AttributeBonusPointHandler_LoadedModelTest {

  private ModelIdentifier modelIdentifier;
  private AttributeBonusPointHandler bonusPointHandler;
  private IModelResourceHandler resourceHandler;

  @Before
  public void createHandler() throws Exception {
    ICharacterId characterId = EasyMock.createMock(ICharacterId.class);
    modelIdentifier = new ModelIdentifier(characterId, "net.sf.anathema.character.attributes.model"); //$NON-NLS-1$
    IModelCollection modelCollection = CharacterObjectMother.createModelProvider(
        modelIdentifier,
        new DummyTraitCollection());
    resourceHandler = EasyMock.createMock(IModelResourceHandler.class);
    bonusPointHandler = new AttributeBonusPointHandler(modelCollection, resourceHandler);
  }

  @Test
  public void modelAlreadyLoadedIsUsedForCalculation() throws Exception {
    IResource resource = ResourceObjectMother.createExistingResource();
    EasyMock.expect(resourceHandler.getResource(modelIdentifier)).andStubReturn(resource);
    EasyMock.replay(resourceHandler);
    assertEquals(0, bonusPointHandler.getPoints(modelIdentifier.getCharacterId()));
  }

  @Test
  public void setsNoMarkerWithCalculationIfResourceDoesNotExist() throws Exception {
    IFile resource = EasyMock.createMock(IFile.class);
    EasyMock.expect(resource.exists()).andStubReturn(false);
    EasyMock.replay(resource);
    EasyMock.expect(resourceHandler.getResource(modelIdentifier)).andStubReturn(resource);
    EasyMock.replay(resourceHandler);
    bonusPointHandler.getPoints(modelIdentifier.getCharacterId());
    verify(resource);
  }
}