package character.acceptance.tests;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

import character.acceptance.InteractionTraitList;

@SuppressWarnings("nls")
public abstract class AbstractEssenceTest extends AbstractAcceptanceTest {

  private InteractionTraitList traitList;

  @Before
  public final void createInteraction() throws Exception {
    traitList = character.createTraitInteraction(IPluginConstants.MODEL_ID);
  }

  protected final IInteractiveTrait getEssenceTrait() {
    return traitList.getTrait(new Identificate("Essence"));
  }

  @Test
  public final void startsWithEssenceValue() throws Exception {
    IInteractiveTrait essence = getEssenceTrait();
    assertThat(essence.getValue(), is(getStartValue()));
  }

  @Test
  public final void cannotReduceEssenceBelowStartValue() throws Exception {
    IInteractiveTrait essence = getEssenceTrait();
    int startValue = essence.getValue();
    essence.setValue(startValue - 1);
    assertThat(essence.getValue(), is(startValue));
  }

  protected abstract int getStartValue();
}