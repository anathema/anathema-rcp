package charactertype.solar.acceptance;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.character.freebies.plugin.ICharmFreebiesConstants;

import org.junit.Test;

public class SolarCharmFreebies_Test extends AbstractDefaultSolarTest {

  @Test
  public void hasFiveCheapFreebies() throws Exception {
    assertThat(character.getCredit(ICharmFreebiesConstants.CHEAP_CREDIT_ID), is(5));
  }

  @Test
  public void hasFiveUnrestrictedFreebies() throws Exception {
    assertThat(character.getCredit(ICharmFreebiesConstants.UNRESTRICTED_CREDIT_ID), is(5));
  }
}