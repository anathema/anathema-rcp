package character.acceptance.tests;

import static character.acceptance.IAcceptanceConstants.*;
import static character.acceptance.TraitAssert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

@SuppressWarnings("nls")
public abstract class AbstractEssenceUserEssence_Test extends AbstractEssenceTest {

  @Override
  protected final int getStartValue() {
    return 2;
  }

  @Test
  public final void canRaiseEssenceAboveStartValue() throws Exception {
    assertCanRaise(getEssenceTrait());
  }

  @Test
  public final void spentsNoBonusPointsOnEssenceOfStartValue() throws Exception {
    assertThat(character.getBonusPoints(ESSENCE_POINT_CONFIGURATION), is(0));
  }

  @Test
  public final void spentsCorrectBonusPointsOnEssenceForOnePointOfIncrement() throws Exception {
    increaseByOne(getEssenceTrait());
    assertThat(character.getBonusPoints(ESSENCE_POINT_CONFIGURATION), is(getBonuspointsForOneIncrement()));
  }

  protected abstract int getBonuspointsForOneIncrement();

  @Test
  public final void spentsCorrectXpOnEssenceForIncrementFrom2To3() throws Exception {
    character.setExperienced();
    increaseByOne(getEssenceTrait());
    assertThat(character.getXpSpent(ESSENCE_POINT_CONFIGURATION), is(getXpForIncrementFrom2To3()));
  }

  protected abstract int getXpForIncrementFrom2To3();
}