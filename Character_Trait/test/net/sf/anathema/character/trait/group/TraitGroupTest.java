package net.sf.anathema.character.trait.group;

import static org.junit.Assert.*;

import org.junit.Test;

public class TraitGroupTest {

  private TraitGroup group;

  @org.junit.Before
  public void createTraitGroup() throws Exception {
    group = new TraitGroup("id"); //$NON-NLS-1$
  }

  @Test
  public void equalsIfIdIdentical() throws Exception {
    TraitGroup otherGroup = new TraitGroup("id"); //$NON-NLS-1$
    assertTrue(group.equals(otherGroup));
  }

  @Test
  public void doesNotEqualIfIdNotIdentical() throws Exception {
    TraitGroup otherGroup = new TraitGroup("ad"); //$NON-NLS-1$
    assertFalse(group.equals(otherGroup));
  }

  @Test
  public void doesNotEqualIfOtherIdNull() throws Exception {
    TraitGroup otherGroup = new TraitGroup(null);
    assertFalse(group.equals(otherGroup));
  }

  @Test
  public void doesNotEqualIfOwnIdNull() throws Exception {
    TraitGroup otherGroup = new TraitGroup(null);
    assertFalse(otherGroup.equals(group));
  }
}
