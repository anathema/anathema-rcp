package net.sf.anathema.character.attributes.points;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.anathema.character.attributes.AttributeObjectMother;
import net.sf.anathema.character.attributes.model.AttributeTemplate;
import net.sf.anathema.character.attributes.model.Attributes;
import net.sf.anathema.character.attributes.model.IAttributes;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.trait.rules.TraitTemplate;

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
    IAttributes attributes = Attributes.create(new AttributeTemplate().getGroups(), new TraitTemplate());
    attributes.getTraits()[0].getCreationModel().setValue(attributeValue);
    IModelProvider modelProvider = AttributeObjectMother.createModelProvider(attributes);
    PrioritylessAttributeFreebies freebies = new PrioritylessAttributeFreebies(modelProvider);
    assertEquals(freebiesPoints, freebies.getPoints(null, AttributePointCalculator.PRIMARY, 3));
  }
}