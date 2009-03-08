package charactertype.db.acceptance;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.freebies.virtues.VirtueFreebiesConstants;

import org.junit.Test;

public class DynastDbVirtueFreebies_Test extends AbstractDynastDbTest{

  @Test
  public void hasThreeVirtueFreebies() throws Exception {
    assertThat(character.getCredit(VirtueFreebiesConstants.FREEBIE_CREDIT), is(5));
  }
}