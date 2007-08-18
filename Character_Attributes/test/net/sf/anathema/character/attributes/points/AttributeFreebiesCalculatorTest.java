package net.sf.anathema.character.attributes.points;

import static net.sf.anathema.character.attributes.points.AttributeFreebiesCalculator.*;
import static org.junit.Assert.assertEquals;
import net.sf.anathema.character.attributes.model.Attributes;
import net.sf.anathema.character.attributes.model.IAttributes;

import org.junit.Before;
import org.junit.Test;

public class AttributeFreebiesCalculatorTest {

  private DummyTraitGroup[] traitGroups;

  @Before
  public void createGroups() {
    traitGroups = new DummyTraitGroup[] { new DummyTraitGroup("first"), //$NON-NLS-1$
        new DummyTraitGroup("second"), //$NON-NLS-1$
        new DummyTraitGroup("third") }; //$NON-NLS-1$
  }

  @Test
  public void zeroPointsSpentForEmptyAttributes() throws Exception {
    IAttributes attributes = Attributes.create(traitGroups);
    assertPointsSpent(0, 0, 0, attributes);
  }

  @Test
  public void oneAttributeProvidesCreationValueForPrimaryGroup() throws Exception {
    traitGroups[0].addTraitId("trait"); //$NON-NLS-1$
    IAttributes attributes = Attributes.create(traitGroups);
    attributes.getTrait("trait").getCreationModel().setValue(2); //$NON-NLS-1$
    assertPointsSpent(2, 0, 0, attributes);
  }


  @Test
  public void higherAttributeFromDifferentGroupsProvidesPrimaryGroupPointsSpent() throws Exception {
    traitGroups[0].addTraitId("lower"); //$NON-NLS-1$
    traitGroups[1].addTraitId("higher"); //$NON-NLS-1$
    IAttributes attributes = Attributes.create(traitGroups);
    attributes.getTrait("lower").getCreationModel().setValue(2); //$NON-NLS-1$
    attributes.getTrait("higher").getCreationModel().setValue(5); //$NON-NLS-1$
    assertPointsSpent(5, 2, 0, attributes);
  }

  private void assertPointsSpent(int primary, int secondary, int tertiary, IAttributes attributes) {
    AttributeFreebiesCalculator freebiesCalculator = new AttributeFreebiesCalculator(attributes, traitGroups);
    assertEquals(primary, freebiesCalculator.pointsSpentFor(PRIMARY));
    assertEquals(secondary, freebiesCalculator.pointsSpentFor(SECONDARY));
    assertEquals(tertiary, freebiesCalculator.pointsSpentFor(TERTIARY));
  }
}