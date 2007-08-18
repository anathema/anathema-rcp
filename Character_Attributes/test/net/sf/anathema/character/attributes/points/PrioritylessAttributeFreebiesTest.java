package net.sf.anathema.character.attributes.points;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.anathema.character.attributes.model.AttributeTemplate;
import net.sf.anathema.character.attributes.model.Attributes;
import net.sf.anathema.character.attributes.model.IAttributes;
import net.sf.anathema.character.core.model.IModelIdentifier;
import net.sf.anathema.character.core.model.IModelProvider;

import org.easymock.EasyMock;
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
    IAttributes attributes = Attributes.create(new AttributeTemplate().getGroups());
    attributes.getTraits()[0].getCreationModel().setValue(attributeValue);
    IModelProvider modelProvider = createModelProvider(attributes);
    PrioritylessAttributeFreebies freebies = new PrioritylessAttributeFreebies(modelProvider);
    assertEquals(freebiesPoints, freebies.getPoints(null, AttributePointCalculator.PRIMARY, 3));
  }

  private IModelProvider createModelProvider(IAttributes attributes) {
    IModelProvider modelProvider = EasyMock.createNiceMock(IModelProvider.class);
    EasyMock.expect(modelProvider.getModel(EasyMock.isA(IModelIdentifier.class))).andReturn(attributes);
    EasyMock.replay(modelProvider);
    return modelProvider;
  }
}