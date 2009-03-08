package charactertype.solar.acceptance;

import static character.acceptance.IAcceptanceConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Test;

import character.acceptance.tests.AbstractVirtues_Test;

public class SolarVirtues_Test extends AbstractVirtues_Test {

  @Override
  protected String getTemplateId() {
    return IIntegrationConstants.DEFAULT_TEMPLATE;
  }
  
  @Test
  public final void spentsNoBonusPointsOnVirtuesOf4() throws Exception {
    traitList.getTrait(new Identificate("Valor")).setValue(4);
    assertThat(character.getBonusPoints(VIRTUES_POINT_CONFIGURATION), is(0));
  }
}