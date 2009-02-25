package charactertype.mortal.acceptance;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;

import org.junit.Test;

import character.acceptance.tests.AbstractEssenceTest;

public class HeroicMortalEssenceTest extends AbstractEssenceTest {

  @Test
  public void cannotRaiseEssenceAboveStartValue() throws Exception {
    IInteractiveTrait essence = getEssenceTrait();
    int startValue = essence.getValue();
    essence.setValue(startValue + 1);
    assertThat(essence.getValue(), is(startValue));
  }

  @Override
  protected int getStartValue() {
    return 1;
  }

  @Override
  protected String getTemplateId() {
    return IAcceptanceConstants.HEROIC_TEMPLATE_ID;
  }
}