package net.sf.anathema.charms.tree;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class CharmId_EqualsTest {

  @Test
  public void equalsCharmIdViaCompleteId() throws Exception {
    assertThat(new CharmId("Pattern.Test", null), is(new CharmId("Pattern.{0}", "Test"))); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  }

  @Test
  public void doesNotEqualObject() throws Exception {
    assertThat(new Object(), is(not((Object) new CharmId("Pattern.Test", null)))); //$NON-NLS-1$
  }
}