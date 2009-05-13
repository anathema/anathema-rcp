package net.sf.anathema.character.spiritualtraits.points.virtues;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.lib.lang.ICalculator;

import org.junit.Before;
import org.junit.Test;

public class VirtuesBonusPointCalculatorFactory_Test extends AbstractVirtuesBonusPoint_Test {

  private ICalculator calculator;

  @Before
  public void createCalculator() throws Exception {
    this.calculator = new VirtuesBonuspointCalculatorFactory(collection).create();
  }

  @Test
  public void calculatesNoBonusPointsForIncreasedEssence() throws Exception {
    setIncreasedCreationValue(essence);
    assertThat(calculator.calculate(), is(0));
  }

  @Test
  public void calculatesThreeBonusPointsForIncreasedCompassion() throws Exception {
    setIncreasedCreationValue(willpower);
    assertThat(calculator.calculate(), is(3));
  }

  @Test
  public void calculatesThreeBonusPointsForIncreasedConviction() throws Exception {
    setIncreasedCreationValue(conviction);
    assertThat(calculator.calculate(), is(3));
  }

  @Test
  public void calculatesThreeBonusPointsForIncreasedTemperance() throws Exception {
    setIncreasedCreationValue(temperance);
    assertThat(calculator.calculate(), is(3));
  }

  @Test
  public void calculatesThreeBonusPointsForIncreasedValor() throws Exception {
    setIncreasedCreationValue(valor);
    assertThat(calculator.calculate(), is(3));
  }

  @Test
  public void sumsUpBonusPointsForIncrements() throws Exception {
    setIncreasedCreationValue(valor);
    setIncreasedCreationValue(temperance);
    assertThat(calculator.calculate(), is(6));
  }
}