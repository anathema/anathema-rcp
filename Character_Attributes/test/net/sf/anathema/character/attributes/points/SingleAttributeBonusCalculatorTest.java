package net.sf.anathema.character.attributes.points;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.collection.TraitCollection;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class SingleAttributeBonusCalculatorTest {


  private BasicTrait basicTrait;
  private AttributeBonusPointCalculator calculator;

  private void assertCalculation(int creationValue, int expectedPoints) {
    basicTrait.getCreationModel().setValue(creationValue);
    assertEquals(expectedPoints, calculator.calculate());
  }

  @Before
  public void createCalculator() {
    this.basicTrait = new BasicTrait(new Identificate("Hasäntümlichkeit")); //$NON-NLS-1$
    this.calculator = new AttributeBonusPointCalculator(new TraitCollection(basicTrait));
  }

  @Test
  public void noBonusPointsForReducedAttribute() throws Exception {
    assertCalculation(0, 0);
  }


  @Test
  public void noBonusPointsForNoIncrease() throws Exception {
    assertCalculation(1, 0);
  }

  @Test
  public void calculates4PointsForCreationValue2() throws Exception {
    assertCalculation(2, 4);
  }
  
  @Test
  public void calculate3PointsForFavoredCreationValue2() throws Exception {
    basicTrait.getFavoredModel().setValue(true);
    assertCalculation(2, 3);
  }

  @Test
  public void calculates8PointsCreationValue2() throws Exception {
    assertCalculation(3, 8);
  }
}