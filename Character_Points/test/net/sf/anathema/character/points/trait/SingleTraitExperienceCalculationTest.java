package net.sf.anathema.character.points.trait;

import static org.junit.Assert.*;
import net.sf.anathema.character.points.trait.TraitCollectionExperienceCalculator;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.collection.TraitCollection;
import net.sf.anathema.character.trait.status.FavoredStatus;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class SingleTraitExperienceCalculationTest {

  private BasicTrait basicTrait;
  private TraitCollectionExperienceCalculator calculator;

  private void assertCalculation(int creationValue, int experienceValue, int expectedPoints) {
    basicTrait.getCreationModel().setValue(creationValue);
    basicTrait.getExperiencedModel().setValue(experienceValue);
    assertEquals(expectedPoints, calculator.calculate());
  }

  private void assertFavoredCalculation(int creationValue, int experienceValue, int expectedPoints) {
    basicTrait.getStatusManager().setStatus(new FavoredStatus());
    assertCalculation(creationValue, experienceValue, expectedPoints);
  }

  @Before
  public void createCalculator() {
    this.basicTrait = new BasicTrait(new Identificate("Hasäntümlichkeit")); //$NON-NLS-1$
    this.calculator = new TraitCollectionExperienceCalculator(new TraitCollection(basicTrait));
  }

  @Test
  public void noExperiencePointsSpentForNoExperienceValue() throws Exception {
    assertEquals(0, calculator.calculate());
  }

  @Test
  public void calculates3PointsForExperienceValue2AndCreationValue1OnFavored() throws Exception {
    assertFavoredCalculation(1, 2, 3);
  }

  @Test
  public void calculates10PointsForExperienceValue3AndCreationValue1OnFavored() throws Exception {
    assertFavoredCalculation(1, 3, 10);
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
  
  @Test
  public void calculates0PointsForExperienceValueLowerThanCreationValue() throws Exception {
    assertCalculation(5, 4, 0);
  }
}