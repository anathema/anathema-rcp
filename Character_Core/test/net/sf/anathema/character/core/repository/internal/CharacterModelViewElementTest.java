package net.sf.anathema.character.core.repository.internal;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.eclipse.ui.IWorkbenchPage;
import org.junit.Test;

public class CharacterModelViewElementTest {
  @Test
  public void cannotBeDeleted() throws Exception {
    assertFalse(new CharacterModelViewElement(null, null, null).canBeDeleted());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void doesNotSupportDeletion() throws Exception {
    new CharacterModelViewElement(null, null, null).delete(EasyMock.createMock(IWorkbenchPage.class));
  }
}
