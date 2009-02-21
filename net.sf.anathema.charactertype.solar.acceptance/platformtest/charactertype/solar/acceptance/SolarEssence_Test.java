package charactertype.solar.acceptance;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.acceptance.InteractionTraitList;
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class SolarEssence_Test extends AbstractDefaultSolarTest {

  private InteractionTraitList traitList;

  @Before
  public void createInteraction() throws Exception {
    traitList = character.createTraitInteraction(IPluginConstants.MODEL_ID);
  }

  @Test
  public void startsWithEssenceValueOf2() throws Exception {
    IInteractiveTrait essence = getEssenceTrait();
    assertThat(essence.getValue(), is(2));
  }

  @Test
  public void cannotReduceEssenceBelowStartValue() throws Exception {
    IInteractiveTrait essence = getEssenceTrait();
    int startValue = essence.getValue();
    essence.setValue(startValue - 1);
    assertThat(essence.getValue(), is(startValue));
  }

  @Test
  public void canRaiseEssenceAboveStartValue() throws Exception {
    IInteractiveTrait essence = getEssenceTrait();
    int startValue = essence.getValue();
    int targetValue = startValue + 1;
    essence.setValue(targetValue);
    assertThat(essence.getValue(), is(targetValue));
  }

  private IInteractiveTrait getEssenceTrait() {
    return traitList.getTrait(new Identificate("Essence"));
  }
}