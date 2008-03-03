package net.sf.anathema.character.freebies.abilities;

import static org.junit.Assert.*;
import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.fake.DummyCharacterId;
import net.sf.anathema.character.core.fake.DummyModelCollection;
import net.sf.anathema.character.freebies.abilities.util.IAbilityFreebiesConstants;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.freebies.fake.CreditManagerObjectMother;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.collection.TraitCollection;
import net.sf.anathema.character.trait.status.DefaultStatus;
import net.sf.anathema.character.trait.status.FavoredStatus;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class DefaultFreebiesHandlerTest {

  private static final int FAVORED_CREDIT = 2;
  private static final String FAVORED2 = "Favored2"; //$NON-NLS-1$
  private static final String FAVORED1 = "Favored1"; //$NON-NLS-1$
  private static final String NON_FAVORED2 = "NonFavored2"; //$NON-NLS-1$
  private static final String NON_FAVORED1 = "NonFavored1"; //$NON-NLS-1$
  private DefaultFreebiesHandler handler;
  private TraitCollection model;
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
    model = new TraitCollection(
        createFavoredTrait(FAVORED1),
        createFavoredTrait(FAVORED2),
        createNonFavoredTrait(NON_FAVORED1),
        createNonFavoredTrait(NON_FAVORED2));
    modelCollection.addModel(IAbilitiesPluginConstants.MODEL_ID, model);
    ICreditManager creditManager = CreditManagerObjectMother.createCreditManager(
        characterId,
        IAbilityFreebiesConstants.FAVORED_CREDIT,
        FAVORED_CREDIT);
    handler = new DefaultFreebiesHandler(modelCollection, creditManager);
  }

  @Test
  public void returnsZeroPointsForUnmodifiedTraits() throws Exception {
    assertEquals(0, handler.getPoints(characterId, 5));
  }

  @Test
  public void returnsLowUnfavoredAttributeValue() throws Exception {
    model.getTrait(NON_FAVORED1).getCreationModel().setValue(3);
    assertEquals(3, handler.getPoints(characterId, 5));
  }

  @Test
  public void returnsLowSumUnfavoredAttributeValues() throws Exception {
    model.getTrait(NON_FAVORED1).getCreationModel().setValue(3);
    model.getTrait(NON_FAVORED2).getCreationModel().setValue(1);
    assertEquals(4, handler.getPoints(characterId, 5));
  }

  @Test
  public void returnsCreditValueForExceedingUnfavoredSpentSum() throws Exception {
    model.getTrait(NON_FAVORED1).getCreationModel().setValue(5);
    assertEquals(3, handler.getPoints(characterId, 3));
  }

  @Test
  public void returnsZeroPointsIfOnlyFavoredCreditIsSpentOnFavored() throws Exception {
    model.getTrait(FAVORED1).getCreationModel().setValue(FAVORED_CREDIT);
    assertEquals(0, handler.getPoints(characterId, 3));
  }

  @Test
  public void returnsPointsSpentOnFavoredAboveFaoredCredit() throws Exception {
    model.getTrait(FAVORED1).getCreationModel().setValue(FAVORED_CREDIT + 2);
    assertEquals(2, handler.getPoints(characterId, 3));
  }
}