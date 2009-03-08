package charactertype.solar.acceptance;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.freebies.virtues.VirtueFreebiesConstants;

import org.junit.Test;

public class SolarVirtueFreebies_Test extends AbstractDefaultSolarTest {

  @Test
  public void hasFiveFreebies() throws Exception {
    assertThat(character.getCredit(VirtueFreebiesConstants.FREEBIE_CREDIT), is(5));
  }
}