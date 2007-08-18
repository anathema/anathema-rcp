package net.sf.anathema.character.attributes.points;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class AttributeFreebiesCalculatorTest {

  private AttributeFreebiesCalculator freebiesCalculator;
  private DummyAttributes attributes;

  @Before
  public void createFreebiesCalculator() {
    attributes = new DummyAttributes();
    freebiesCalculator = new AttributeFreebiesCalculator(attributes);
  }
  
  @Test
  public void zeroPointsSpentForEmptyAttributes() throws Exception {
    assertPointsSpent(0, 0, 0);
  }
  
  @Test
  public void oneAttributeProvidesCreationValueForPrimaryGroup() throws Exception {
    attributes.addTrait(createTrait("Hasä", 2)); //$NON-NLS-1$
    assertPointsSpent(2, 0, 0);
  }

  private void assertPointsSpent(int primary, int secondary, int tertiary) {
    assertEquals(primary, freebiesCalculator.calculate(AttributeFreebiesCalculator.PRIMARY).getPointsSpent());
    assertEquals(secondary, freebiesCalculator.calculate(AttributeFreebiesCalculator.SECONDARY).getPointsSpent());
    assertEquals(tertiary, freebiesCalculator.calculate(AttributeFreebiesCalculator.TERTIARY).getPointsSpent());
  }

  private BasicTrait createTrait(String id, int creationValue) {
    BasicTrait trait = new BasicTrait(new Identificate(id));
    trait.getCreationModel().setValue(creationValue);
    return trait;
  }
}