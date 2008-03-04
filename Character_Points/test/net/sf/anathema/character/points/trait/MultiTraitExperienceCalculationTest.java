package net.sf.anathema.character.points.trait;

import static org.junit.Assert.*;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.collection.TraitCollection;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class MultiTraitExperienceCalculationTest {

  private BasicTrait firstTrait;
  private BasicTrait secondTrait;
  private TraitCollectionExperienceCalculator calculator;

  @Before
  public void createCalculator() {
    firstTrait = new BasicTrait(new Identificate("Hasäntümlichkeit")); //$NON-NLS-1$
    secondTrait = new BasicTrait(new Identificate("Hasänstärke")); //$NON-NLS-1$
    calculator = new TraitCollectionExperienceCalculator(new TraitCollection(firstTrait, secondTrait), 4, 100);
  }

  @Test
  public void noExperiencePointsSpentForNoExperienceValue() throws Exception {
    assertEquals(0, calculator.calculate());
  }

  @Test
  public void spends8ExperiencePointsFor2Increases() throws Exception {
    firstTrait.getCreationModel().setValue(1);
    secondTrait.getCreationModel().setValue(1);
    firstTrait.getExperiencedModel().setValue(2);
    secondTrait.getExperiencedModel().setValue(2);
    assertEquals(8, calculator.calculate());
  }
}