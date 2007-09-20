package net.sf.anathema.character.attributes.points;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TertiaryAttributeFreebiesHandlerTest {

  @Test
  public void priorityGroupIsTertiary() throws Exception {
    TertiaryAttributeFreebies handler = new TertiaryAttributeFreebies(null);
    assertEquals(AttributePointCalculator.PriorityGroup.Tertiary, handler.getPriority());
  }
}