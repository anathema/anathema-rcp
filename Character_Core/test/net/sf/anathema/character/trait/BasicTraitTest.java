package net.sf.anathema.character.trait;

import net.sf.anathema.lib.util.Identificate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BasicTraitTest {

  private IBasicTrait trait;

  @Before
  public void createTrait() {
    this.trait = new BasicTrait(new Identificate("test")); //$NON-NLS-1$
  }

  @Test
  public void initiallyHasNegativeExperiencedValue() throws Exception {
    Assert.assertTrue(0 > trait.getExperiencedModel().getValue());
  }

  public void intialCreationValueIs0() throws Exception {
    Assert.assertEquals(0, trait.getCreationModel().getValue());
  }
}
