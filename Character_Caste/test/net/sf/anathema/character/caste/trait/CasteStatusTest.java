package net.sf.anathema.character.caste.trait;

import static org.junit.Assert.*;
import net.sf.anathema.character.trait.status.FavoredStatus;

import org.junit.Before;
import org.junit.Test;

public class CasteStatusTest {

  private CasteStatus status;

  @Before
  public void createStatus() {
    this.status = new CasteStatus();
  }

  @Test
  public void isCheap() throws Exception {
    assertTrue(status.isCheap());
  }

  @Test
  public void isNotModifiable() throws Exception {
    assertFalse(status.isModifiable());
  }
  
  @Test
  public void casteStatusEqualsCasteStatus() throws Exception {
    assertEquals(new CasteStatus(), new CasteStatus());
  }
  
  @Test
  public void casteStatusDoesNotEqualFavoredStatus() throws Exception {
    assertFalse(new CasteStatus().equals(new FavoredStatus()));
  }
}