package charactertype.mortal.acceptance;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.acceptance.InteractionTraitList;
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class HeroicMortalEssenceTest extends AbstractHeroicMortalTest {

  private InteractionTraitList traitList;

  @Before
  public void createInteraction() throws Exception {
    traitList = character.createTraitInteraction(IPluginConstants.MODEL_ID);
  }

  @Test
  public void startsWithEssenceValueOf1() throws Exception {
    IInteractiveTrait essence = getEssenceTrait();
    assertThat(essence.getValue(), is(1));
  }

  @Test
  public void cannotReduceEssenceBelowStartValue() throws Exception {
    IInteractiveTrait essence = getEssenceTrait();
    int startValue = essence.getValue();
    essence.setValue(startValue - 1);
    assertThat(essence.getValue(), is(startValue));
  }

  @Test
  public void cannotRaiseEssenceAboveStartValue() throws Exception {
    IInteractiveTrait essence = getEssenceTrait();
    int startValue = essence.getValue();
    essence.setValue(startValue + 1);
    assertThat(essence.getValue(), is(startValue));
  }

  private IInteractiveTrait getEssenceTrait() {
    return traitList.getTrait(new Identificate("Essence"));
  }
}