package net.sf.anathema.character.freebies.abilities;

import static org.junit.Assert.*;
import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.fake.DummyCharacterId;
import net.sf.anathema.character.core.fake.DummyModelCollection;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.collection.TraitCollection;
import net.sf.anathema.character.trait.status.FavoredStatus;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class FavoredFreebiesHandler_Test {

  private static final String FAVORED2 = "Favored2"; //$NON-NLS-1$
  private static final String FAVORED1 = "Favored1"; //$NON-NLS-1$
  private static final String NON_FAVORED2 = "NonFavored2"; //$NON-NLS-1$
  private static final String NON_FAVORED1 = "NonFavored1"; //$NON-NLS-1$
  private FavoredFreebiesHandler handler;
  private TraitCollection model;
  private ICharacterId characterId;

  private BasicTrait createFavoredTrait(String id) {
    BasicTrait basicTrait = createNonFavoredTrait(id);
    basicTrait.getStatusManager().setStatus(new FavoredStatus());
    return basicTrait;
  }

  private BasicTrait createNonFavoredTrait(String id) {
    return new BasicTrait(new Identificate(id));
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
    handler = new FavoredFreebiesHandler(modelCollection);
  }

  @Test
  public void returnsZeroPointsForUnmodifiedAbilities() throws Exception {
    assertEquals(0, handler.getPoints(characterId, 5));
  }

  @Test
  public void returnsFavoredAbiltyValueForLowValue() throws Exception {
    model.getTrait(FAVORED1).getCreationModel().setValue(2);
    assertEquals(2, handler.getPoints(characterId, 5));
  }

  @Test
  public void returnsSumOfFavoredAbiltyValueForLowSum() throws Exception {
    model.getTrait(FAVORED1).getCreationModel().setValue(2);
    model.getTrait(FAVORED2).getCreationModel().setValue(1);
    assertEquals(3, handler.getPoints(characterId, 5));
  }

  @Test
  public void returnsZeroPointsIfOnlyUnfavoredAreIncreased() throws Exception {
    model.getTrait(NON_FAVORED1).getCreationModel().setValue(2);
    assertEquals(0, handler.getPoints(characterId, 5));
  }


  @Test
  public void returnsPointsAccordingToCreditIfMoreFavoredDotsAreSpent() throws Exception {
    model.getTrait(FAVORED1).getCreationModel().setValue(3);
    assertEquals(2, handler.getPoints(characterId, 2));
  }

  @Test
  public void returnsThreePointsForHighTrait() throws Exception {
    model.getTrait(FAVORED1).getCreationModel().setValue(5);
    assertEquals(3, handler.getPoints(characterId, 5));
  }

  @Test
  public void usesFreebiesForSubtraits() throws Exception {
    model.getTrait(FAVORED1).getCreationModel().setValue(5);
    BasicTrait subTrait = BasicTraitObjectMother.createFavored("Tum"); //$NON-NLS-1$
    model.addSubTrait(FAVORED1, subTrait);
    subTrait.getCreationModel().setValue(2);
    assertEquals(2, handler.getPoints(characterId, 5));
  }
}