package charactertype.db.acceptance;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.charms.character.model.ICharmModel;

import org.junit.Test;

public class DynastDbModeAvailability_Test extends AbstractDynastDbTest {

  @Test
  public void supportsAbilityModel() throws Exception {
    assertThat(character.getTemplate().supportsModel(IAbilitiesPluginConstants.MODEL_ID), is(true));
  }

  @Test
  public void supportsCharmModel() throws Exception {
    assertThat(character.getTemplate().supportsModel(ICharmModel.MODEL_ID), is(true));
  }
}