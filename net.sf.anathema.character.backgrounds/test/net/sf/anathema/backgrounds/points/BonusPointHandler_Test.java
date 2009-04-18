package net.sf.anathema.backgrounds.points;

import static net.sf.anathema.character.core.fake.CharacterObjectMother.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.backgrounds.BackgroundModel;
import net.sf.anathema.character.backgrounds.points.BonusPointHandler;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.fake.DummyCharacterId;
import net.sf.anathema.character.trait.IBasicTrait;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class BonusPointHandler_Test {

  private BonusPointHandler handler;
  private BackgroundModel model;
  private ICharacterId characterId;

  @Before
  public void createHandler() throws Exception {
    this.characterId = new DummyCharacterId();
    this.model = new BackgroundModel();
    ModelIdentifier modelIdentifier = new ModelIdentifier(characterId, "net.sf.anathema.character.backgrounds.model");
    IModelCollection modelCollection = createModelProvider(modelIdentifier, model);
    handler = new BonusPointHandler(modelCollection);
  }

  @Test
  public void calculatesZeroPointsForEmptyModel() throws Exception {
    assertCalculatesPoints(0);
  }

  @Test
  public void calculatesOnePointForBackgroundWithOneDot() throws Exception {
    IBasicTrait background = model.addBackground("Allies (Koumyou)");
    background.getCreationModel().setValue(1);
    assertCalculatesPoints(1);
  }

  @Test
  public void calculatesZeroPointForBackgroundWithoutDot() throws Exception {
    model.addBackground("Allies (Koumyou)");
    assertCalculatesPoints(0);
  }

  @Test
  public void factorsInPointsAboveThreeForExpensiveTwo() throws Exception {
    model.addBackground("Allies (Koumyou)").getCreationModel().setValue(4);
    assertCalculatesPoints(5);
  }

  @Test
  public void calculatesTwoPointsForTwoBackgroundWithOneDot() throws Exception {
    model.addBackground("Allies (Koumyou)").getCreationModel().setValue(1);
    model.addBackground("Nemesis (Samea)").getCreationModel().setValue(1);
    assertCalculatesPoints(2);
  }

  private void assertCalculatesPoints(int expected) {
    assertThat(handler.getPoints(characterId), is(expected));
  }
}