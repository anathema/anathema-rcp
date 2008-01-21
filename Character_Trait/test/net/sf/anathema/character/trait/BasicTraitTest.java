package net.sf.anathema.character.trait;

import static org.junit.Assert.*;
import net.sf.anathema.character.trait.status.DefaultStatus;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class BasicTraitTest {

  private IBasicTrait trait;

  @Before
  public void createTrait() {
    this.trait = new BasicTrait(new Identificate("test")); //$NON-NLS-1$
  }

  @Test
  public void hasNegativeExperiencedValue() throws Exception {
    assertTrue(0 > trait.getExperiencedModel().getValue());
  }
  
  @Test
  public void isNotFavored() throws Exception {
    assertTrue(trait.getStatusManager().getStatus() instanceof DefaultStatus);
  }
  
  public void hasCreationValue0() throws Exception {
    assertEquals(0, trait.getCreationModel().getValue());
  }
}