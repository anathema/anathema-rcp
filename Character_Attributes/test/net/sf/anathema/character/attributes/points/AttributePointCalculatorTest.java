package net.sf.anathema.character.attributes.points;

import static net.sf.anathema.character.attributes.points.AttributePointCalculator.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.attributes.model.Attributes;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.rules.TraitTemplate;

import org.junit.Before;
import org.junit.Test;

public class AttributePointCalculatorTest {

  private DummyTraitGroup[] traitGroups;

  @Before
  public void createGroups() {
    traitGroups = new DummyTraitGroup[] { new DummyTraitGroup("first"), //$NON-NLS-1$
        new DummyTraitGroup("second"), //$NON-NLS-1$
        new DummyTraitGroup("third") }; //$NON-NLS-1$
  }

  @Test
  public void zeroPointsSpentForEmptyAttributes() throws Exception {
    ITraitCollectionModel attributes = Attributes.create(traitGroups, new TraitTemplate());
    assertPointsSpent(0, 0, 0, attributes);
  }

  @Test
  public void oneAttributeProvidesCreationValueForPrimaryGroup() throws Exception {
    traitGroups[0].addTraitId("trait"); //$NON-NLS-1$
    ITraitCollectionModel attributes = Attributes.create(traitGroups, new TraitTemplate());
    attributes.getTrait("trait").getCreationModel().setValue(2); //$NON-NLS-1$
    assertPointsSpent(1, 0, 0, attributes);
  }


  @Test
  public void higherAttributeFromDifferentGroupsProvidesPrimaryGroupPointsSpent() throws Exception {
    traitGroups[0].addTraitId("lower"); //$NON-NLS-1$
    traitGroups[1].addTraitId("higher"); //$NON-NLS-1$
    ITraitCollectionModel attributes = Attributes.create(traitGroups, new TraitTemplate());
    attributes.getTrait("lower").getCreationModel().setValue(2); //$NON-NLS-1$
    attributes.getTrait("higher").getCreationModel().setValue(5); //$NON-NLS-1$
    assertPointsSpent(4, 1, 0, attributes);
  }

  private void assertPointsSpent(int primary, int secondary, int tertiary, ITraitCollectionModel attributes) {
    AttributePointCalculator freebiesCalculator = new AttributePointCalculator(attributes, traitGroups);
    assertEquals(primary, freebiesCalculator.pointsSpentFor(PriorityGroup.Primary));
    assertEquals(secondary, freebiesCalculator.pointsSpentFor(PriorityGroup.Secondary));
    assertEquals(tertiary, freebiesCalculator.pointsSpentFor(PriorityGroup.Tertiary));
  }
}