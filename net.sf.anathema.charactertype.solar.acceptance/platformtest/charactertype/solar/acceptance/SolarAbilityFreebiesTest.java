package charactertype.solar.acceptance;

import static org.junit.Assert.*;
import net.sf.anathema.character.freebies.abilities.util.IAbilityFreebiesConstants;

import org.junit.Test;

public class SolarAbilityFreebiesTest extends AbstractDefaultSolarTest {

  @Test
  public void hasUnlimitedCredit18() throws Exception {
    assertEquals(18, character.getCredit(IAbilityFreebiesConstants.UNRESTRICTED_CREDIT));
  }

  @Test
  public void hasFavoredCredit10() throws Exception {
    assertEquals(10, character.getCredit(IAbilityFreebiesConstants.FAVORED_CREDIT));
  }
}