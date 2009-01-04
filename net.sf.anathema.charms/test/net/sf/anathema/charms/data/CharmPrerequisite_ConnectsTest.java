package net.sf.anathema.charms.data;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.tree.DummyCharmId;

import org.junit.Test;

public class CharmPrerequisite_ConnectsTest {

  private static final String OTHER_ID = "otherId"; //$NON-NLS-1$
  private static final String ID = "id"; //$NON-NLS-1$

  @Test
  public void connectsSourceCharm() throws Exception {
    assertThat(new CharmPrerequisite(new DummyCharmId(ID), new DummyCharmId(OTHER_ID)).connects(ID), is(true));
  }

  @Test
  public void connectsDestinationCharm() throws Exception {
    assertThat(new CharmPrerequisite(null, new DummyCharmId(ID)).connects(ID), is(true));
  }

  @Test
  public void doesNotConnectUnknownCharm() throws Exception {
    assertThat(new CharmPrerequisite(null, new DummyCharmId(OTHER_ID)).connects(ID), is(false));
  }
}