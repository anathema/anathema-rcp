package net.sf.anathema.character.attributes.points;

import static org.junit.Assert.*;
import net.sf.anathema.character.attributes.model.Attributes;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class MultiAttributesBonusPointCalculationTest {

  private BasicTrait firstTrait;
  private BasicTrait secondTrait;
  private AttributeBonusPointCalculator calculator;

  @Before
  public void createCalculator() {
    this.firstTrait = new BasicTrait(new Identificate("Hasäntümlichkeit")); //$NON-NLS-1$
    this.secondTrait = new BasicTrait(new Identificate("Hasänstärke")); //$NON-NLS-1$
    this.calculator = new AttributeBonusPointCalculator(new Attributes(firstTrait, secondTrait));
  }

  @Test
  public void spends8BonusPointsFor2Increases() throws Exception {
    firstTrait.getCreationModel().setValue(2);
    secondTrait.getCreationModel().setValue(2);
    assertEquals(8, calculator.calculate());
  }
}