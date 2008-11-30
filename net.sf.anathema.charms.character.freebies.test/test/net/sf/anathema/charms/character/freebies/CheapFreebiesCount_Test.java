package net.sf.anathema.charms.character.freebies;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class CheapFreebiesCount_Test {

  @Test
  public void returnsCheapCharmCountIfDoesNotExhaustCredit() throws Exception {
    CheapFreebiesCount count = new CheapFreebiesCount(new DummyCount(2), 3);
    assertThat(count.count(), is(2));
  }

  @Test
  public void returnsCreditIfCheapCharmCountExceeds() throws Exception {
    CheapFreebiesCount count = new CheapFreebiesCount(new DummyCount(4), 3);
    assertThat(count.count(), is(3));
  }
}