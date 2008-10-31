package net.sf.anathema.character.attributes.points;

import static org.junit.Assert.*;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.fake.CharacterObjectMother;
import net.sf.anathema.character.trait.fake.DummyTraitCollection;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class AttributeBonusPointHandler_LoadedModelTest {

  private ModelIdentifier modelIdentifier;
  private AttributeBonusPointHandler bonusPointHandler;

  @Before
  public void createHandler() throws Exception {
    ICharacterId characterId = EasyMock.createMock(ICharacterId.class);
    modelIdentifier = new ModelIdentifier(characterId, "net.sf.anathema.character.attributes.model"); //$NON-NLS-1$
    IModelCollection modelCollection = CharacterObjectMother.createModelProvider(
        modelIdentifier,
        new DummyTraitCollection());
    bonusPointHandler = new AttributeBonusPointHandler(modelCollection);
  }

  @Test
  public void modelAlreadyLoadedIsUsedForCalculation() throws Exception {
    assertEquals(0, bonusPointHandler.getPoints(modelIdentifier.getCharacterId()));
  }
}