package charactertype.db.acceptance;

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
public class DynastDbEssence_Test extends AbstractDynastDbTest {

  private InteractionTraitList traitList;

  @Before
  public void createInteraction() throws Exception {
    traitList = character.createTraitInteraction(IPluginConstants.MODEL_ID);
  }

  private IInteractiveTrait getEssenceTrait() {
    return traitList.getTrait(new Identificate("Essence"));
  }

  @Test
  public void startsWithEssenceValueOf2() throws Exception {
    IInteractiveTrait essence = getEssenceTrait();
    assertThat(essence.getValue(), is(2));
  }

  @Test
  public void cannotReduceEssenceBelowStartValue() throws Exception {
    assertCannotLower(getEssenceTrait());
  }

  @Test
  public void canRaiseEssenceAboveStartValue() throws Exception {
    assertCanRaise(getEssenceTrait());
  }

  @Test
  public void spentsNoBonusPointsOnEssenceOfStartValue() throws Exception {
    assertThat(character.getBonusPoints(ESSENCE_POINT_CONFIGURATION), is(0));
  }

  @Test
  public void spentsTenBonusPointsOnEssenceForOnePointOfIncrement() throws Exception {
    increaseByOne(getEssenceTrait());
    assertThat(character.getBonusPoints(ESSENCE_POINT_CONFIGURATION), is(10));
  }

  @Test
  public void spents20XpOnEssenceForIncrementFrom2To3() throws Exception {
    character.setExperienced();
    increaseByOne(getEssenceTrait());
    assertThat(character.getXpSpent(ESSENCE_POINT_CONFIGURATION), is(20));
  }
}