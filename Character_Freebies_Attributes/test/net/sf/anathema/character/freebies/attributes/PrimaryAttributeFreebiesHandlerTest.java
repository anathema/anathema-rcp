package net.sf.anathema.character.freebies.attributes;

import static org.junit.Assert.*;

import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator;

import org.junit.Test;

public class PrimaryAttributeFreebiesHandlerTest {

  @Test
  public void priorityGroupIsPrimary() throws Exception {
    PrimaryAttributeFreebies handler = new PrimaryAttributeFreebies(null, null);
    assertEquals(AttributePointCalculator.Priority.Primary, handler.getPriority());
  }
}