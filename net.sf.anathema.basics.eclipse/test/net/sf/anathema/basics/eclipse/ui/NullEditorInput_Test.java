package net.sf.anathema.basics.eclipse.ui;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class NullEditorInput_Test {

  @Test
  public void returnsNullForAdapter() throws Exception {
    NullEditorInput input = new NullEditorInput();
    Object adapter = input.getAdapter(Object.class);
    assertThat(adapter, is(nullValue()));
  }
}