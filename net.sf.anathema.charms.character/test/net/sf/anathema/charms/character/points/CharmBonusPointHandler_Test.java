package net.sf.anathema.charms.character.points;

import static org.easymock.EasyMock.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.fake.DummyModelCollection;
import net.sf.anathema.charms.character.CharmModel;
import net.sf.anathema.charms.character.ICharmModel;

import org.junit.Before;
import org.junit.Test;

public class CharmBonusPointHandler_Test {

  private static final String CHEAP_CHARM = "cheapCharm"; //$NON-NLS-1$
  private CharmBonusPointHandler pointHandler;
  private ICharacterId characterId;
  private CharmModel charmModel;

  @Before
  public void createHandler() {
    characterId = createMock(ICharacterId.class);
    replay(characterId);
    DummyModelCollection modelCollection = new DummyModelCollection();
    charmModel = new CharmModel();
    modelCollection.addModel(ICharmModel.MODEL_ID, charmModel);
    pointHandler = new CharmBonusPointHandler(modelCollection, createPredicateFactory());
  }

  private ICharmCostFactory createPredicateFactory() {
    ICharmCostFactory factory = createMock(ICharmCostFactory.class);
    expect(factory.create(characterId)).andReturn(createCharmCost());
    replay(factory);
    return factory;
  }

  private ICharmCost createCharmCost() {
    DummyCharmCost charmCost = new DummyCharmCost(5, 100);
    charmCost.setBonusCost(CHEAP_CHARM, 4);
    return charmCost;
  }

  @Test
  public void calculates0BonusPointForNoLearnedCharm() throws Exception {
    assertThat(pointHandler.getPoints(characterId), is(0));
  }

  @Test
  public void calculates5BonusPointForExpensiveCharm() throws Exception {
    charmModel.toggleCreationLearned("myCreationLearnedCharm"); //$NON-NLS-1$
    assertThat(pointHandler.getPoints(characterId), is(5));
  }

  @Test
  public void calculates10BonusPointForTwoExpensiveCharms() throws Exception {
    charmModel.toggleCreationLearned("myCreationLearnedCharm"); //$NON-NLS-1$
    charmModel.toggleCreationLearned("hisCreationLearnedCharm"); //$NON-NLS-1$
    assertThat(pointHandler.getPoints(characterId), is(10));
  }

  @Test
  public void calculates4BonusPointForCheapCharm() throws Exception {
    charmModel.toggleCreationLearned(CHEAP_CHARM);
    assertThat(pointHandler.getPoints(characterId), is(4));
  }
}