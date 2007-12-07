package net.sf.anathema.character.attributes.points;

import net.sf.anathema.basics.eclipse.resource.fake.ResourceObjectMother;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.IModelResourceHandler;
import net.sf.anathema.character.trait.fake.DummyTraitCollection;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class AttributeBonusPointHandler_LoadedModelTest {

  private ModelIdentifier modelIdentifier;
  private AttributeBonusPointHandler bonusPointHandler;
  private IModelResourceHandler resourceHandler;
  private IModelCollection modelCollection;

  @Before
  public void createHandler() throws Exception {
    ICharacterId characterId = EasyMock.createMock(ICharacterId.class);
    modelIdentifier = new ModelIdentifier(characterId, "net.sf.anathema.character.attributes.model"); //$NON-NLS-1$
    modelCollection = EasyMock.createMock(IModelCollection.class);
    resourceHandler = EasyMock.createMock(IModelResourceHandler.class);
    bonusPointHandler = new AttributeBonusPointHandler(modelCollection, resourceHandler);
  }

  @Test
  public void modelAlreadyLoadedIsUsedForCalculation() throws Exception {
    EasyMock.expect(modelCollection.contains(modelIdentifier)).andStubReturn(true);
    EasyMock.expect(modelCollection.getModel(modelIdentifier)).andStubReturn(new DummyTraitCollection());
    EasyMock.expect(resourceHandler.getResource(modelIdentifier)).andStubReturn(
        ResourceObjectMother.createExistingResource());
    EasyMock.replay(modelCollection, resourceHandler);
    bonusPointHandler.getPoints(modelIdentifier.getCharacterId());
    EasyMock.verify(modelCollection, resourceHandler);
  }
}