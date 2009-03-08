package character.acceptance.tests;

import static character.acceptance.IAcceptanceConstants.*;
import static character.acceptance.TraitAssert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

import character.acceptance.InteractionTraitList;

@SuppressWarnings("nls")
public abstract class AbstractVirtues_Test extends AbstractAcceptanceTest {

  protected InteractionTraitList traitList;

  @Before
  public void createInteraction() throws Exception {
    traitList = character.createTraitInteraction(IPluginConstants.MODEL_ID);
  }

  private IInteractiveTrait[] getVirtues() {
    return new IInteractiveTrait[] {
        traitList.getTrait(new Identificate("Compassion")),
        traitList.getTrait(new Identificate("Conviction")),
        traitList.getTrait(new Identificate("Temperance")),
        traitList.getTrait(new Identificate("Valor")) };
  }

  @Test
  public final void startsVirtuesWithValueOf1() throws Exception {
    for (final IInteractiveTrait virtue : getVirtues()) {
      assertThat(virtue.getValue(), is(1));
    }
  }

  @Test
  public final void cannotReduceNoVirtueBelowStartValue() throws Exception {
    for (final IInteractiveTrait virtue : getVirtues()) {
      assertCannotLower(virtue);
    }
  }

  @Test
  public final void canRaiseAnyVirtueAboveStartValue() throws Exception {
    for (final IInteractiveTrait virtue : getVirtues()) {
      assertCanRaise(virtue);
    }
  }

  @Test
  public final void spentsNoBonusPointsOnVirtuesOfStartValue() throws Exception {
    assertThat(character.getBonusPoints(VIRTUES_POINT_CONFIGURATION), is(0));
  }

  @Test
  public final void spents3XpOnIncreasingAVirtueFrom1To2OnExperience() throws Exception {
    character.setExperienced();
    for (final IInteractiveTrait virtue : getVirtues()) {
      virtue.setValue(2);
      assertThat(character.getXpSpent(VIRTUES_POINT_CONFIGURATION), is(3));
      virtue.setValue(1);
    }
  }
}