package net.sf.anathema.basics.repository.treecontent;

import static org.junit.Assert.assertTrue;

import org.easymock.EasyMock;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.junit.Test;

public class ViewElementDeleterTest {

  @Test
  public void deletesElement() throws Exception {
    DummyResourceViewElement element = new DummyResourceViewElement();
    IWorkbenchPage page = EasyMock.createNiceMock(IWorkbenchPage.class);
    EasyMock.expect(page.getEditorReferences()).andReturn(new IEditorReference[0]);
    EasyMock.replay(page);
    ViewElementDeleter deleter = new ViewElementDeleter();
    deleter.setPage(page);
    deleter.delete(element);
    assertTrue(element.isDeleted());
  }
}