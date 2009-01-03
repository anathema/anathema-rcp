package net.sf.anathema.charms.tree.operations;

import static net.sf.anathema.charms.tree.CharmTreeExtensionPointObjectMother.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

import org.junit.Before;
import org.junit.Test;

public class ForGenerics_Test {

  private static final String RIGHT_TYPE = "rightType"; //$NON-NLS-1$
  private IExtensionElement element;

  @Before
  public void createElement() throws Exception {
    element = createGenericCharms(RIGHT_TYPE);
  }

  @Test
  public void acceptsCorrectCharacterTypeGenerics() throws Exception {
    assertThat(new ForGenerics(RIGHT_TYPE).evaluate(element), is(true));
  }

  @Test
  public void rejectsWrongCharacterTypeGenerics() throws Exception {
    assertThat(new ForGenerics("wrongType").evaluate(element), is(false)); //$NON-NLS-1$
  }
}