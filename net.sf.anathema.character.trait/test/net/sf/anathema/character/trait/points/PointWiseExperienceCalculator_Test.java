package net.sf.anathema.character.trait.points;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;

import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.IBasicTrait;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class PointWiseExperienceCalculator_Test {

  private static final int COST = 3;
  private IBasicTrait firstTrait;
  private IBasicTrait secondTrait;
  private ICalculator calculator;

  @Before
  public void createCalculator() throws Exception {
    this.firstTrait = new BasicTrait("First");
    this.secondTrait = new BasicTrait("Second");
    this.calculator = new PointWiseExperienceCalculator(COST, Arrays.asList(firstTrait, secondTrait));
  }

  @Test
  public void calculatesNoPointsForSameCreationAndExperienceValue() throws Exception {
    firstTrait.getCreationModel().setValue(4);
    firstTrait.getExperiencedModel().setValue(4);
    assertThat(calculator.calculate(), is(0));
  }

  @Test
  public void calculatesCostsForDifferenceBetweenHighExperienceAndCreationValue() throws Exception {
    firstTrait.getCreationModel().setValue(4);
    firstTrait.getExperiencedModel().setValue(6);
    assertThat(calculator.calculate(), is(COST * 2));
  }

  @Test
  public void calculatesCostsForAllTraits() throws Exception {
    firstTrait.getExperiencedModel().setValue(2);
    secondTrait.getExperiencedModel().setValue(4);
    assertThat(calculator.calculate(), is(COST * 6));
  }

  @Test
  public void calculatesNoPointsForLowExperienceValue() throws Exception {
    firstTrait.getCreationModel().setValue(4);
    firstTrait.getExperiencedModel().setValue(2);
    assertThat(calculator.calculate(), is(0));
  }
}