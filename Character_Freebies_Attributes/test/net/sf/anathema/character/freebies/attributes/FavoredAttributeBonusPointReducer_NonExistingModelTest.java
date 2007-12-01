package net.sf.anathema.character.freebies.attributes;

import static org.junit.Assert.*;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.fake.CharacterObjectMother;
import net.sf.anathema.character.core.fake.DummyCharacterId;
import net.sf.anathema.character.freebies.configuration.ICreditManager;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class FavoredAttributeBonusPointReducer_NonExistingModelTest {

  private FavoredAttributeBonusPointReducer reducer;
  private ICharacterId characterId = new DummyCharacterId();

  @Before
  public void createReducer() {
    ICreditManager creditManager = EasyMock.createNiceMock(ICreditManager.class);
    EasyMock.expect(creditManager.getCredit(characterId, "net.sf.anthema.character.attributes.freebies.favored"))
        .andReturn(2)
        .anyTimes();
    EasyMock.replay(creditManager);
    reducer = new FavoredAttributeBonusPointReducer(
        CharacterObjectMother.createNonLoadingEmptyModelProvider(),
        CharacterObjectMother.createEmptyResourceHandler(),
        creditManager);
  }

  @Test
  public void calculatedZeroReduction() throws Exception {
    assertEquals(0, reducer.getPoints(characterId));
  }
}