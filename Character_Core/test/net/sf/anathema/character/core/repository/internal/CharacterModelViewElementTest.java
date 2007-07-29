package net.sf.anathema.character.core.repository.internal;

import static org.junit.Assert.assertNull;
import net.sf.anathema.basics.repository.treecontent.deletion.IPageDelible;

import org.junit.Test;

public class CharacterModelViewElementTest {

  @Test
  public void doesNotAdaptToDelible() {
    assertNull(new CharacterModelViewElement(null, null, null).getAdapter(IPageDelible.class));
  }
}
