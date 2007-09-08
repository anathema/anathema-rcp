package net.sf.anathema.character.attributes.points;

import static org.junit.Assert.*;
import net.sf.anathema.character.attributes.model.CreditManagerObjectMother;
import net.sf.anathema.character.core.fake.DummyCharacterId;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class FavoredAttributeBonusPointReducerTest {

  private FavoredAttributeBonusPointReducer reducer;
  private ICharacterId characterId = new DummyCharacterId();
  private IFreebiesHandler handler;
  private int credit = 0;

  @Before
  public void createReducer() {
    ICreditManager creditManager = CreditManagerObjectMother.createCreditManager(
        characterId,
        "net.sf.anthema.character.attributes.freebies.favored", //$NON-NLS-1$
        credit);
    handler = EasyMock.createMock(IFreebiesHandler.class);
    reducer = new FavoredAttributeBonusPointReducer(handler, creditManager);
  }

  @Test
  public void returns0ForUnusedCredit() throws Exception {
    EasyMock.expect(handler.getPoints(characterId, credit)).andReturn(0);
    EasyMock.replay(handler);
    assertEquals(0, reducer.getPoints(characterId));
  }

  @Test
  public void multipliesPointsSpentByFavoredAttributeBonusPointCost() throws Exception {
    EasyMock.expect(handler.getPoints(characterId, credit)).andReturn(1);
    EasyMock.replay(handler);
    assertEquals(-3, reducer.getPoints(characterId));
  }
}