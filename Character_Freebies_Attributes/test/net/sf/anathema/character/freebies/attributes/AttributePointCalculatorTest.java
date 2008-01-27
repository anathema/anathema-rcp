package net.sf.anathema.character.freebies.attributes;

import static org.junit.Assert.*;

import java.util.Map;

import net.sf.anathema.character.attributes.model.Attributes;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.PriorityGroup;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.fake.DummyTraitGroup;
import net.sf.anathema.character.trait.template.EssenceSensitiveTraitTemplate;

import org.junit.Before;
import org.junit.Ignore;
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
    ITraitCollectionModel attributes = Attributes.create(traitGroups, new EssenceSensitiveTraitTemplate());
    assertPointsSpent(0, 0, 0, attributes);
  }

  @Test
  public void oneAttributeProvidesCreationValueForPrimaryGroup() throws Exception {
    traitGroups[0].addTraitId("trait"); //$NON-NLS-1$
    ITraitCollectionModel attributes = Attributes.create(traitGroups, new EssenceSensitiveTraitTemplate());
    attributes.getTrait("trait").getCreationModel().setValue(2); //$NON-NLS-1$
    assertPointsSpent(1, 0, 0, attributes);
  }


  @Test
  @Ignore("Test für Case 120")
  // TODO Case 120
  public void higherAttributeFromDifferentGroupsProvidesPrimaryGroupPointsSpent() throws Exception {
    traitGroups[0].addTraitId("lower"); //$NON-NLS-1$
    traitGroups[1].addTraitId("higher"); //$NON-NLS-1$
    ITraitCollectionModel attributes = Attributes.create(traitGroups, new EssenceSensitiveTraitTemplate());
    attributes.getTrait("lower").getCreationModel().setValue(2); //$NON-NLS-1$
    attributes.getTrait("higher").getCreationModel().setValue(5); //$NON-NLS-1$
    assertPointsSpent(4, 1, 0, attributes);
  }

  private void assertPointsSpent(int primary, int secondary, int tertiary, ITraitCollectionModel attributes) {
    Map<PriorityGroup, Integer> creditsByGroup = new AttributePriorityFreebies().getEmpty();
    AttributePointCalculator freebiesCalculator = new AttributePointCalculator(creditsByGroup, attributes, traitGroups);
    assertEquals(primary, freebiesCalculator.dotsFor(PriorityGroup.Primary).spentTotally());
    assertEquals(secondary, freebiesCalculator.dotsFor(PriorityGroup.Secondary).spentTotally());
    assertEquals(tertiary, freebiesCalculator.dotsFor(PriorityGroup.Tertiary).spentTotally());
  }
}