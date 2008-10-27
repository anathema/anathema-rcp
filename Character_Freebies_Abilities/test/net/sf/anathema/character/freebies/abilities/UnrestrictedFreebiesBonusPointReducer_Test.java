package net.sf.anathema.character.freebies.abilities;

import static org.junit.Assert.*;
import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.fake.DummyCharacterId;
import net.sf.anathema.character.core.fake.DummyModelCollection;
import net.sf.anathema.character.freebies.abilities.credit.CreditManagerObjectMother;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.fake.DummyTraitCollection;
import net.sf.anathema.character.trait.status.DefaultStatus;
import net.sf.anathema.character.trait.status.FavoredStatus;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class UnrestrictedFreebiesBonusPointReducer_Test {

  private static final int FAVORED_CREDIT = 2;
  private static final int UNRESTRICTED_CREDIT = 5;
  private static final String FAVORED2 = "Favored2"; //$NON-NLS-1$
  private static final String FAVORED1 = "Favored1"; //$NON-NLS-1$
  private static final String NON_FAVORED2 = "NonFavored2"; //$NON-NLS-1$
  private static final String NON_FAVORED1 = "NonFavored1"; //$NON-NLS-1$
  private UnrestrictedFreebiesBonusPointReducer reducer;
  private DummyTraitCollection model;
  private ICharacterId characterId;

  private BasicTrait createFavoredTrait(String id) {
    BasicTrait basicTrait = new BasicTrait(new Identificate(id));
    basicTrait.getStatusManager().setStatus(new FavoredStatus());
    return basicTrait;
  }

  private BasicTrait createNonFavoredTrait(String id) {
    BasicTrait basicTrait = new BasicTrait(new Identificate(id));
    basicTrait.getStatusManager().setStatus(new DefaultStatus());
    return basicTrait;
  }

  @Before
  public void createHandlerWithTwoFavoredAndTwoUnfavoredTraits() {
    characterId = new DummyCharacterId();
    DummyModelCollection modelCollection = new DummyModelCollection();
    model = new DummyTraitCollection();
    model.addTrait(
        createFavoredTrait(FAVORED1),
        createFavoredTrait(FAVORED2),
        createNonFavoredTrait(NON_FAVORED1),
        createNonFavoredTrait(NON_FAVORED2));
    modelCollection.addModel(IAbilitiesPluginConstants.MODEL_ID, model);
    ICreditManager creditManager = CreditManagerObjectMother.create(FAVORED_CREDIT, UNRESTRICTED_CREDIT, characterId);
    reducer = new UnrestrictedFreebiesBonusPointReducer(modelCollection, creditManager);
  }

  @Test
  public void reducesByZeroPointsWithoutTraitModification() throws Exception {
    assertEquals(0, reducer.calculatePoints(model, characterId));
  }

  @Test
  public void reducesByZeroWithOneFavoredTraitAtFavoredCreditLimit() throws Exception {
    model.getTrait(FAVORED1).getCreationModel().setValue(FAVORED_CREDIT);
    assertEquals(0, reducer.calculatePoints(model, characterId));
  }

  @Test
  public void reducesBy1WithOneFavoredTraitOneDotAboveFreeCredit() throws Exception {
    model.getTrait(FAVORED1).getCreationModel().setValue(FAVORED_CREDIT + 1);
    assertEquals(-1, reducer.calculatePoints(model, characterId));
  }

  @Test
  public void reducesByTwoForOneDotInExpensiveTrait() throws Exception {
    model.getTrait(NON_FAVORED1).getCreationModel().setValue(1);
    assertEquals(-2, reducer.calculatePoints(model, characterId));
  }

  @Test
  public void reducesBy2ForEachExpensiveSubtraitDot() throws Exception {
    model.getTrait(NON_FAVORED1).getCreationModel().setValue(1);
    BasicTrait nonFavoredSubtrait = new BasicTrait("MeSubtrait"); //$NON-NLS-1$
    BasicTrait secondNonFavoredSubtrait = new BasicTrait("MeTooSubtraitDesu"); //$NON-NLS-1$
    model.addSubTrait(NON_FAVORED1, nonFavoredSubtrait);
    model.addSubTrait(NON_FAVORED1, secondNonFavoredSubtrait);
    nonFavoredSubtrait.getCreationModel().setValue(2);
    secondNonFavoredSubtrait.getCreationModel().setValue(1);
    assertEquals(-6, reducer.calculatePoints(model, characterId));
  }

  @Test
  public void reducesBy1ForEachCheapSubtraitDotInExcessOfFavoredCredit() throws Exception {
    BasicTrait favoredSubtrait = new BasicTrait("MeSubtrait"); //$NON-NLS-1$
    BasicTrait secondFavoredSubtrait = new BasicTrait("MeTooSubtraitDesu"); //$NON-NLS-1$
    favoredSubtrait.getCreationModel().setValue(FAVORED_CREDIT);
    secondFavoredSubtrait.getCreationModel().setValue(3);
    favoredSubtrait.getStatusManager().setStatus(new FavoredStatus());
    secondFavoredSubtrait.getStatusManager().setStatus(new FavoredStatus());
    model.addSubTrait(FAVORED1, favoredSubtrait);
    model.addSubTrait(FAVORED1, secondFavoredSubtrait);
    assertEquals(-3, reducer.calculatePoints(model, characterId));
  }

  @Test
  public void doesNotConsiderSubtraitValuesAbove3() throws Exception {
    BasicTrait favoredSubtrait = new BasicTrait("MeSubtrait"); //$NON-NLS-1$
    favoredSubtrait.getCreationModel().setValue(4);
    favoredSubtrait.getStatusManager().setStatus(new FavoredStatus());
    model.addSubTrait(FAVORED1, favoredSubtrait);
    assertEquals(-1, reducer.calculatePoints(model, characterId));
  }

  @Test
  public void reducesByMaximalTenDots() throws Exception {
    model.getTrait(FAVORED1).getCreationModel().setValue(3);
    model.getTrait(FAVORED2).getCreationModel().setValue(3);
    model.getTrait(NON_FAVORED1).getCreationModel().setValue(3);
    model.getTrait(NON_FAVORED2).getCreationModel().setValue(3);
    assertEquals(-10, reducer.calculatePoints(model, characterId));
  }
}