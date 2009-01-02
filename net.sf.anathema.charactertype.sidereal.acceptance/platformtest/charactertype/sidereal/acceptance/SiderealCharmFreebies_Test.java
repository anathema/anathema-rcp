package charactertype.sidereal.acceptance;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.character.freebies.plugin.ICharmFreebiesConstants;

import org.junit.Test;

public class SiderealCharmFreebies_Test extends AbstractDefaultSiderealTest {

  @Test
  public void hasFiveCheapFreebies() throws Exception {
    assertThat(character.getCredit(ICharmFreebiesConstants.CHEAP_CREDIT_ID), is(5));
  }

  @Test
  public void hasSevenUnrestrictedFreebies() throws Exception {
    assertThat(character.getCredit(ICharmFreebiesConstants.UNRESTRICTED_CREDIT_ID), is(7));
  }
}