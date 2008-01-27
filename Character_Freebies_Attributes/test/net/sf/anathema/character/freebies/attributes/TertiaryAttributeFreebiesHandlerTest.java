package net.sf.anathema.character.freebies.attributes;

import static org.junit.Assert.*;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator;

import org.junit.Test;

public class TertiaryAttributeFreebiesHandlerTest {

  @Test
  public void priorityGroupIsTertiary() throws Exception {
    TertiaryAttributeFreebies handler = new TertiaryAttributeFreebies(null, null);
    assertEquals(AttributePointCalculator.PriorityGroup.Tertiary, handler.getPriority());
  }
}