package net.sf.anathema.charms.character.freebies;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class UnrestrictedFreebiesCount_Test {

  @Test
  public void returnsCharmCountIfDoesNotExhaustCredit() throws Exception {
    ICount count = new UnrestrictedFreebiesCount(new DummyCount(2), 0, 3);
    assertThat(count.count(), is(2));
  }

  @Test
  public void returnsCreditIfCharmCountExceedsCredit() throws Exception {
    ICount count = new UnrestrictedFreebiesCount(new DummyCount(4), 0, 3);
    assertThat(count.count(), is(3));
  }

  @Test
  public void reducesCountByOneForEachCheapFreeby() throws Exception {
    ICount count = new UnrestrictedFreebiesCount(new DummyCount(4), 2, 3);
    assertThat(count.count(), is(2));
  }
}