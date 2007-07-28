package net.sf.anathema.basics.repository.treecontent.itemtype;

import static org.junit.Assert.assertFalse;

import org.easymock.EasyMock;
import org.eclipse.ui.IWorkbenchPage;
import org.junit.Test;

public class ItemTypeViewElementTest {
  @Test
  public void cannotBeDeleted() throws Exception {
    assertFalse(new ItemTypeViewElement(null).canBeDeleted());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void doesNotSupportDeletion() throws Exception {
    new ItemTypeViewElement(null).delete(EasyMock.createMock(IWorkbenchPage.class));
  }
}
