package net.sf.anathema.character.freebies.attributes;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.fake.CharacterObjectMother;
import net.sf.anathema.character.core.model.IModelResourceHandler;
import net.sf.anathema.character.freebies.attributes.AttributeFreebiesBonusPointReducer_ExistingModelTest.DummyCreditManager;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class AttributeFreebiesBonutPointReducer_NonExistingModelTest {

  private AttributeFreebiesBonusPointReducer reducer;
  private ICharacterId characterId;

  @Before
  public void createReducer() throws Exception {
    this.characterId = EasyMock.createMock(ICharacterId.class);
    IModelCollection modelProvider = CharacterObjectMother.createNonLoadingEmptyModelProvider();
    IModelResourceHandler resourceManager = CharacterObjectMother.createEmptyResourceHandler();
    this.reducer = new AttributeFreebiesBonusPointReducer(modelProvider, resourceManager, new DummyCreditManager());
  }

  @Test
  public void hasZeroPoints() throws Exception {
    assertEquals(0, reducer.getPoints(characterId));
  }
}