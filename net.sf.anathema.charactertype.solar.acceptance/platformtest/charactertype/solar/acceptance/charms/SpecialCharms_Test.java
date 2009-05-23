package charactertype.solar.acceptance.charms;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.character.model.IVirtualCharms;
import net.sf.anathema.charms.character.model.VirtualCharms;

import org.junit.Test;

@SuppressWarnings("nls")
public class SpecialCharms_Test {

  @Test
  public void anyExcellencyIsVirtual() throws Exception {
    IVirtualCharms charmEvaluation = new VirtualCharms();
    assertThat(charmEvaluation.isVirtual("solar.any.{0}.excellency"), is(true));
  }
}