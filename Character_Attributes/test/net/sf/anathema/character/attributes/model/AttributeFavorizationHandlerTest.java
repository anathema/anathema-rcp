package net.sf.anathema.character.attributes.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class AttributeFavorizationHandlerTest {

  @Test
  public void favorizationCount() throws Exception {
    AttributeFavorizationHandler favorizationHandler = new AttributeFavorizationHandler(null, new AttributeTemplate(1), null);
    assertEquals(1, favorizationHandler.getFavoredCount());
  }
}