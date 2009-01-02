package net.sf.anathema.charms.data;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.charms.tree.DummyCharmId;

import org.junit.Test;

public class CharmPrerequisite_ConnectsTest {

  @Test
  public void connectsSourceCharm() throws Exception {
    assertThat(new CharmPrerequisite(new DummyCharmId("id"), new DummyCharmId("otherId")).connects("id"), is(true));
  }

  @Test
  public void connectsDestinationCharm() throws Exception {
    assertThat(new CharmPrerequisite(null, new DummyCharmId("id")).connects("id"), is(true));
  }

  @Test
  public void doesNotConnectUnknownCharm() throws Exception {
    assertThat(new CharmPrerequisite(null, new DummyCharmId("otherId")).connects("id"), is(false));
  }
}