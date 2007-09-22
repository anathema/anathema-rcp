package net.sf.anathema.character.freebies.attributes;

import static org.junit.Assert.*;
import net.sf.anathema.character.attributes.points.AttributePointCalculator;

import org.junit.Test;

public class SecondaryAttributeFreebiesHandlerTest {

  @Test
  public void priorityGroupIsSecondary() throws Exception {
    SecondaryAttributeFreebies handler = new SecondaryAttributeFreebies(null);
    assertEquals(AttributePointCalculator.PriorityGroup.Secondary, handler.getPriority());
  }
}