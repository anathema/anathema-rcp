package net.sf.anathema.character.spiritualtraits.points.virtues;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.trait.points.ICalculator;

import org.junit.Before;
import org.junit.Test;

public class VirtuesXpCalculatorFactory_Test extends AbstractVirtuesBonusPoint_Test {

  private ICalculator calculator;

  @Before
  public void createCalculator() throws Exception {
    this.calculator = new VirtuesXpCalculatorFactory(collection).create();
  }

  @Test
  public void calculatesNoXpForIncreasedEssence() throws Exception {
    setIncreasedExperienceValue(essence);
    assertThat(calculator.calculate(), is(0));
  }

  @Test
  public void calculates3XpForIncreasingCompassionFrom1To2() throws Exception {
    setCreationValue(compassion, 1);
    setIncreasedExperienceValue(compassion);
    assertThat(calculator.calculate(), is(3));
  }

  @Test
  public void calculates6XpForIncreasingConvictionFrom2To3() throws Exception {
    setCreationValue(conviction, 2);
    setIncreasedExperienceValue(conviction);
    assertThat(calculator.calculate(), is(6));
  }

  @Test
  public void calculates9XpForIncreasingTemperanceFrom3To4() throws Exception {
    setCreationValue(temperance, 3);
    setIncreasedExperienceValue(temperance);
    assertThat(calculator.calculate(), is(9));
  }

  @Test
  public void calculates12XpForIncreasingValorFrom4To5() throws Exception {
    setCreationValue(valor, 4);
    setIncreasedExperienceValue(valor);
    assertThat(calculator.calculate(), is(12));
  }

  @Test
  public void sumsUpXpForIncrements() throws Exception {
    setCreationValue(compassion, 1);
    setCreationValue(temperance, 1);
    setIncreasedExperienceValue(compassion);
    setIncreasedExperienceValue(temperance);
    assertThat(calculator.calculate(), is(6));
  }
}