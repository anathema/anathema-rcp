package net.sf.anathema.character.freebies.attributes;

import static org.junit.Assert.*;
import net.sf.anathema.character.attributes.points.AttributePointCalculator;

import org.junit.Test;

public class TertiaryAttributeFreebiesHandlerTest {

  @Test
  public void priorityGroupIsTertiary() throws Exception {
    TertiaryAttributeFreebies handler = new TertiaryAttributeFreebies(null);
    assertEquals(AttributePointCalculator.PriorityGroup.Tertiary, handler.getPriority());
  }
}