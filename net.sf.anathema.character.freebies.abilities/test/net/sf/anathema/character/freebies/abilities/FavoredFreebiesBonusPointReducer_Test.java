package net.sf.anathema.character.freebies.abilities;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.core.fake.DummyCharacterId;
import net.sf.anathema.character.freebies.abilities.credit.FavoredCreditBehaviour;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.fake.DummyTraitCollection;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class FavoredFreebiesBonusPointReducer_Test {

  private static final int FAVORED_CREDIT = 3;
  private DummyCharacterId characterId;
  private DummyTraitCollection model;
  private FavoredFreebiesBonusPointReducer reducer;
  private BasicTrait favoredTrait;
  private BasicTrait nonfavoredTrait;

  @Before
  public void createCollection() {
    model = new DummyTraitCollection();
    favoredTrait = BasicTraitObjectMother.createFavored("FavoredTrait"); //$NON-NLS-1$
    nonfavoredTrait = BasicTraitObjectMother.createFavored("NonfavoredTrait"); //$NON-NLS-1$
    model.addTrait(favoredTrait);
    model.addTrait(nonfavoredTrait);
  }

  @Before
  public void createReducer() {
    characterId = new DummyCharacterId();
    ICreditManager creditManager = EasyMock.createMock(ICreditManager.class);
    new FavoredCreditBehaviour(characterId, FAVORED_CREDIT).configure(creditManager);
    replay(creditManager);
    reducer = new FavoredFreebiesBonusPointReducer(null, creditManager);
  }

  @Test
  public void reducesExpeditureByDotsSpentOnFavoredTrait() throws Exception {
    favoredTrait.getCreationModel().setValue(2);
    assertEquals(-2, reducer.calculatePoints(model, characterId));
  }

  @Test
  public void reducesExpeditureByDotsSpentOnFavoredSubtrait() throws Exception {
    BasicTrait favoredSubTrait = BasicTraitObjectMother.createFavored("Subtrait"); //$NON-NLS-1$
    model.addSubTrait(favoredTrait.getTraitType().getId(), favoredSubTrait);
    favoredTrait.getCreationModel().setValue(1);
    favoredSubTrait.getCreationModel().setValue(2);
    assertEquals(-2, reducer.calculatePoints(model, characterId));
  }
}