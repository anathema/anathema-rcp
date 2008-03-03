package net.sf.anathema.character.freebies.attributes;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.fake.CharacterObjectMother;
import net.sf.anathema.character.core.fake.DummyCharacterId;
import net.sf.anathema.character.core.model.IModelResourceHandler;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;
import net.sf.anathema.character.freebies.fake.CreditManagerObjectMother;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class FavoredAttributeBonusPointReducer_CalculationTest {

  private FavoredAttributeBonusPointReducer reducer;
  private final ICharacterId characterId = new DummyCharacterId();
  private IFreebiesHandler handler;
  private final int credit = 0;

  @Before
  public void createReducer() throws Exception {
    ICreditManager creditManager = CreditManagerObjectMother.createCreditManager(
        characterId,
        "net.sf.anthema.character.attributes.freebies.favored", //$NON-NLS-1$
        credit);
    handler = EasyMock.createNiceMock(IFreebiesHandler.class);
    IModelCollection modelCollection = EasyMock.createNiceMock(IModelCollection.class);
    EasyMock.expect(modelCollection.contains(EasyMock.isA(IModelIdentifier.class))).andStubReturn(true);
    EasyMock.replay(modelCollection);
    IModelResourceHandler resourceHandler = CharacterObjectMother.createFriendlyResourceHandler();
    reducer = new FavoredAttributeBonusPointReducer(modelCollection, resourceHandler, creditManager) {
      @Override
      protected IFreebiesHandler createFreebiesHandler() {
        return handler;
      }
    };
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