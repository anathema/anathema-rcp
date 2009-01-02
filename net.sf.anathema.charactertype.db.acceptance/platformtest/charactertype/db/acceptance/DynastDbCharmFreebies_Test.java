package charactertype.db.acceptance;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.character.freebies.plugin.ICharmFreebiesConstants;

import org.junit.Test;

public class DynastDbCharmFreebies_Test extends AbstractDynastDbTest {

  @Test
  public void hasFourCheapFreebies() throws Exception {
    assertThat(character.getCredit(ICharmFreebiesConstants.CHEAP_CREDIT_ID), is(4));
  }

  @Test
  public void hasThreeUnrestrictedFreebies() throws Exception {
    assertThat(character.getCredit(ICharmFreebiesConstants.UNRESTRICTED_CREDIT_ID), is(3));
  }
}