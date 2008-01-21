package net.sf.anathema.character.trait.status;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DefaultStatusTest {

  private DefaultStatus status;

  @Before
  public void createStatus() {
    status = new DefaultStatus();
  }
  
  @Test
  public void isNotCheap() throws Exception {
    assertFalse(status.isCheap());
  }
  
  @Test
  public void isModifiable() throws Exception {
    assertTrue(status.isModifiable());
  }
}