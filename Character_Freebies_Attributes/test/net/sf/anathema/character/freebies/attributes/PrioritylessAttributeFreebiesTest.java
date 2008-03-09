package net.sf.anathema.character.freebies.attributes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.anathema.character.attributes.model.AttributeGroupTemplate;
import net.sf.anathema.character.attributes.model.DummyTemplateFactory;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator;
import net.sf.anathema.character.freebies.fake.CreditManagerObjectMother;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.collection.TraitCollectionFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class PrioritylessAttributeFreebiesTest {

  private final int attributeValue;
  private final int freebiesPoints;

  @Parameters
  public static Collection< ? > createParameters() {
    return new ArrayList<Object[]>() {
      {
        add(new Object[] { 3, 2 });
        add(new Object[] { 4, 3 });
        add(new Object[] { 5, 3 });
      }
    };
  }

  public PrioritylessAttributeFreebiesTest(int attributeValue, int freebiesPoints) {
    this.attributeValue = attributeValue;
    this.freebiesPoints = freebiesPoints;
  }

  @Test
  public void spentFreebiesFitIntoCredits() throws Exception {
    ITraitCollectionModel attributes = TraitCollectionFactory.create(
        new AttributeGroupTemplate().getGroups(),
        new DummyTemplateFactory());
    attributes.getTraits()[0].getCreationModel().setValue(attributeValue);
    IModelCollection modelProvider = AttributeObjectMother.createModelProvider(attributes);
    PrioritylessAttributeFreebies freebies = new PrioritylessAttributeFreebies(
        modelProvider,
        CreditManagerObjectMother.createNiceManager(),
        AttributePointCalculator.Priority.Primary);
    assertEquals(freebiesPoints, freebies.getPoints(null, 3));
  }
}