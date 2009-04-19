package net.sf.anathema.character.freebies.backgrounds;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.core.fake.DummyModelCollection;

import org.junit.Test;

public class FreebiesHandler_Test {

  @Test
  public void hasBackgroundsFreebiesId() throws Exception {
    DummyModelCollection collection = new DummyModelCollection();
    assertThat(new FreebiesHandler(collection).getCreditId(), is("background.freebies"));
  }
}