package charactertype.solar.acceptance;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.backgrounds.model.IBackgroundModel;

import org.junit.Test;

@SuppressWarnings("nls")
public class SolarBackground_Test extends AbstractDefaultSolarTest {

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

  private void assertSpentsBonusBackground(int expectedPoints) throws Exception {
    assertThat(character.getBonusPoints(BACKGROUND_CONFIGURATION), is(expectedPoints));
  }
}