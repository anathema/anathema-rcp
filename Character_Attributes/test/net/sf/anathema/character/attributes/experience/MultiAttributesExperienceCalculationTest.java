package net.sf.anathema.character.attributes.experience;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.character.attributes.experiencepoints.AttributeExperienceCalculator;
import net.sf.anathema.character.attributes.model.Attributes;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class MultiAttributesExperienceCalculationTest {

  private BasicTrait firstTrait;
  private BasicTrait secondTrait;
  private AttributeExperienceCalculator calculator;

  @Before
  public void createCalculator() {
    this.firstTrait = new BasicTrait(new Identificate("Hasäntümlichkeit")); //$NON-NLS-1$
    this.secondTrait = new BasicTrait(new Identificate("Hasänstärke")); //$NON-NLS-1$
    this.calculator = new AttributeExperienceCalculator(new Attributes(firstTrait, secondTrait));
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