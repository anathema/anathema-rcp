package net.sf.anathema.charms.data;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class CharmContentProvider_Test {

  @Test
  public void returnsEmptyNodeSetForNullId() throws Exception {
     assertThat(new CharmContentProvider().getElements(null), is(nullValue()));
  }
}