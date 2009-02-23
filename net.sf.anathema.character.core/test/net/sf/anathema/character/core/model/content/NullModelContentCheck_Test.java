package net.sf.anathema.character.core.model.content;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.core.character.IModelContainer;

import org.easymock.EasyMock;
import org.junit.Test;

public class NullModelContentCheck_Test {

  @Test
  public void returnsFalse() throws Exception {
    IModelContainer container = EasyMock.createMock(IModelContainer.class);
    String content = ""; //$NON-NLS-1$
    assertThat(new NullModelContentCheck().evaluate(container, content), is(false));
  }
}