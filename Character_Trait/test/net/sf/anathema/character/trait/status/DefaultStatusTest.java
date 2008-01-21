package net.sf.anathema.character.trait.status;

import static org.junit.Assert.*;

import org.junit.Test;

public class DefaultStatusTest {

  @Test
  public void isNotCheap() throws Exception {
    assertFalse(new DefaultStatus().isCheap());
  }
}