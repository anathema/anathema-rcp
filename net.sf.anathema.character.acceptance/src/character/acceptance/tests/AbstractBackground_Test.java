package character.acceptance.tests;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.backgrounds.model.IBackgroundModel;
import net.sf.anathema.character.trait.IBasicTrait;

import org.junit.Test;

@SuppressWarnings("nls")
public abstract class AbstractBackground_Test extends AbstractAcceptanceTest {

  private static final String BACKGROUND_CONFIGURATION = "Backgrounds";

  @Test
  public void spentsNoBonusPointsWithoutBackground() throws Exception {
    assertSpentsBonusBackground(0);
  }

  @Test
  public void spents7BonusPointsOnBackgroundWith5Dots() throws Exception {
    IBackgroundModel model = (IBackgroundModel) character.getModel(IBackgroundModel.MODEL_ID);
    model.addBackground("Resources").getCreationModel().setValue(5);
    assertSpentsBonusBackground(7);
  }

  @Test
  public void spents6XpOnIncreasingBackgroundByTwoOnExperience() throws Exception {
    IBackgroundModel model = (IBackgroundModel) character.getModel(IBackgroundModel.MODEL_ID);
    IBasicTrait background = model.addBackground("Resources");
    background.getCreationModel().setValue(3);
    background.getExperiencedModel().setValue(5);
    assertSpentsXpOnBackgrounds(6);
  }

  private void assertSpentsBonusBackground(int expectedPoints) throws Exception {
    assertThat(character.getBonusPoints(BACKGROUND_CONFIGURATION), is(expectedPoints));
  }

  private void assertSpentsXpOnBackgrounds(int expectedPoints) throws Exception {
    assertThat(character.getXpSpent(BACKGROUND_CONFIGURATION), is(expectedPoints));
  }
}