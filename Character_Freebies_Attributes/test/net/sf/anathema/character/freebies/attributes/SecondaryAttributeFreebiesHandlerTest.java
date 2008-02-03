package net.sf.anathema.character.freebies.attributes;

import static org.junit.Assert.*;

import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator;

import org.junit.Test;

public class SecondaryAttributeFreebiesHandlerTest {

  @Test
  public void priorityGroupIsSecondary() throws Exception {
    SecondaryAttributeFreebies handler = new SecondaryAttributeFreebies(null, null);
    assertEquals(AttributePointCalculator.Priority.Secondary, handler.getPriority());
  }
}