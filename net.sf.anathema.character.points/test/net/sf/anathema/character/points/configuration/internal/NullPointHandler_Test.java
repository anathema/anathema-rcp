package net.sf.anathema.character.points.configuration.internal;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.core.fake.DummyCharacterId;

import org.junit.Test;

public class NullPointHandler_Test {

  @Test
  public void returnsZeroPoints() throws Exception {
    int points = new NullPointHandler().getPoints(new DummyCharacterId());
    assertThat(points, is(0));
  }
}