package net.sf.anathema.character.trait;

import net.sf.anathema.lib.util.Identificate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DisplayTraitTraitTypeTest {

  private IDisplayTrait trait;
  private Identificate traitType;

  @Before
  public void createTrait() {
    this.traitType = new Identificate("test"); //$NON-NLS-1$
    this.trait = new DisplayTrait(new BasicTrait(traitType), null, new DummyTraitRules());
  }

  @Test
  public void hasBasicTraitType() throws Exception {
    Assert.assertEquals(traitType, trait.getTraitType());
  }
}