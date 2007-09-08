package net.sf.anathema.character.attributes.points;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PrimaryAttributeFreebiesHandlerTest {

  @Test
  public void priorityGroupIsPrimary() throws Exception {
    PrimaryAttributeFreebies handler = new PrimaryAttributeFreebies(null);
    assertEquals(AttributePointCalculator.PRIMARY, handler.getPriority());
  }
}