package net.sf.anathema.character.attributes.points;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.character.attributes.model.Attributes;
import net.sf.anathema.character.attributes.points.AttributeExperienceCalculator;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class SingleAttributeExperienceCalculationTest {

  private BasicTrait basicTrait;
  private AttributeExperienceCalculator calculator;

  private void assertCalculation(int creationValue, int experienceValue, int expectedPoints) {
    basicTrait.getCreationModel().setValue(creationValue);
    basicTrait.getExperiencedModel().setValue(experienceValue);
    assertEquals(expectedPoints, calculator.calculate());
  }

  @Before
  public void createCalculator() {
    this.basicTrait = new BasicTrait(new Identificate("Hasäntümlichkeit")); //$NON-NLS-1$
    this.calculator = new AttributeExperienceCalculator(new Attributes(basicTrait));
  }

  @Test
  public void noExperiencePointsSpentForNoExperienceValue() throws Exception {
    assertEquals(0, calculator.calculate());
  }

  @Test
  public void calculates4PointsForExperienceValue2AndCreationValue1() throws Exception {
    assertCalculation(1, 2, 4);
  }

  @Test
  public void calculates4PointsForExperienceValue1AndCreationValue1() throws Exception {
    assertCalculation(1, 1, 0);
  }

  @Test
  public void calculates12PointsForExperienceValue3AndCreationValue1() throws Exception {
    assertCalculation(1, 3, 12);
  }

  @Test
  public void calculates8PointsForExperienceValue3AndCreationValue2() throws Exception {
    assertCalculation(2, 3, 8);
  }
}