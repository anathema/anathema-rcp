package net.sf.anathema.character.trait.status;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FavoredStatusTest {

  private FavoredStatus status;

  @Before
  public void createStatus() {
    status = new FavoredStatus();
  }
  
  @Test
  public void isCheap() throws Exception {
    assertTrue(status.isCheap());
  }
  
  @Test
  public void isModifiable() throws Exception {
    assertTrue(status.isModifiable());
  }
}