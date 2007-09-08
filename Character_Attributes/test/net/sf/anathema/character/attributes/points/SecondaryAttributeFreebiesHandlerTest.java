package net.sf.anathema.character.attributes.points;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SecondaryAttributeFreebiesHandlerTest {

  @Test
  public void priorityGroupIsSecondary() throws Exception {
    SecondaryAttributeFreebies handler = new SecondaryAttributeFreebies(null);
    assertEquals(AttributePointCalculator.SECONDARY, handler.getPriority());
  }
}