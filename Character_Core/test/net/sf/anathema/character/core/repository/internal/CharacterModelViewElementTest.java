package net.sf.anathema.character.core.repository.internal;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.ui.IEditorInputProvider;
import net.sf.anathema.basics.repository.treecontent.deletion.IPageDelible;

import org.junit.Test;

public class CharacterModelViewElementTest {

  @Test
  public void doesNotAdaptToDelible() {
    assertNull(new CharacterModelViewElement(null, null, null).getAdapter(IPageDelible.class));
  }

  @Test
  public void providesEditorInput() {
    assertTrue(new CharacterModelViewElement(null, null, null).getAdapter(IEditorInputProvider.class) instanceof IEditorInputProvider);
  }

}
