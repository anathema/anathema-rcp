package net.sf.anathema.character.trait.status;

import static org.junit.Assert.*;

import org.junit.Test;

public class FavoredStatusTest {

  @Test
  public void isCheap() throws Exception {
    assertTrue(new FavoredStatus().isCheap());
  }
}