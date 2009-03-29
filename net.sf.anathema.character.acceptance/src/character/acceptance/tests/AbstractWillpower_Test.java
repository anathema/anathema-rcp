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
public abstract class AbstractWillpower_Test extends AbstractAcceptanceTest {

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

  private IInteractiveTrait getWillpower() {
    return traitList.getTrait(new Identificate("Willpower"));
  }

  @Test
  public final void startsWithWillpowerOf2() throws Exception {
    assertThat(getWillpower().getValue(), is(2));
  }

  @Test
  public final void respectsMaximumOf8ForLowVirtues() throws Exception {
    getWillpower().setValue(9);
    assertThat(getWillpower().getValue(), is(8));
  }

  @Test
  public final void respectsMinimumOfVirtueSum() throws Exception {
    getWillpower().setValue(1);
    assertThat(getWillpower().getValue(), is(2));
  }

  @Test
  public final void increasesWillpowerWithVirtues() throws Exception {
    setHighestVirtueSumTo9();
    assertThat(getWillpower().getValue(), is(9));
  }

  @Test
  public final void respectsVirtueSumMaximumForHighVirtues() throws Exception {
    setHighestVirtueSumTo9();
    getWillpower().setValue(10);
    assertThat(getWillpower().getValue(), is(9));
  }

  @Test
  public void paysTwoBonusPointsPerDotOfWillpower() throws Exception {
    getWillpower().setValue(3);
    assertThat(character.getBonusPoints(WILLPOWER_POINT_CONFIGURATION), is(2));
  }

  @Test
  public void doesNotPayBonusPointsForDotsCoveredByVirtues() throws Exception {
    setHighestVirtueSumTo9();
    assertThat(character.getBonusPoints(WILLPOWER_POINT_CONFIGURATION), is(0));
  }

  @Test
  public final void spendsNoXpForStartingWillpower() throws Exception {
    character.setExperienced();
    assertThat(character.getXpSpent(WILLPOWER_POINT_CONFIGURATION), is(0));
  }
  
  @Test
  public final void spentsCorrectXpOnWillpowerIncrement() throws Exception {
    character.setExperienced();
    increaseByOne(getWillpower());
    assertThat(character.getXpSpent(WILLPOWER_POINT_CONFIGURATION), is(4));
  }

  private void setHighestVirtueSumTo9() {
    getVirtues()[0].setValue(4);
    getVirtues()[1].setValue(1);
    getVirtues()[2].setValue(1);
    getVirtues()[3].setValue(5);
  }
}