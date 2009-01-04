package net.sf.anathema.character.trait.validator.extension;

import static junit.framework.Assert.*;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.lib.util.Identificate;

import org.junit.Before;
import org.junit.Test;

public class MinimumRequirement_Test {

  private BasicTrait trait;

  @Before
  public void createTrait() throws Exception {
    this.trait = new BasicTrait(new Identificate("Hallo")); //$NON-NLS-1$
  }
  
  @Test
  public void isNotConfirmedForLowCreationValue() throws Exception {
    MinimumRequirement requirement = new MinimumRequirement(trait, 2);
    assertFalse(requirement.isConfirmed());
  }
  
  @Test
  public void isConfirmedForMinimumMatchingCreationValue() throws Exception {
    MinimumRequirement requirement = new MinimumRequirement(trait, 2);
    trait.getCreationModel().setValue(2);
    assertTrue(requirement.isConfirmed());
  }
}