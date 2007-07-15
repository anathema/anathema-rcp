package net.sf.anathema.campaign.plot.repository;

import org.easymock.EasyMock;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.junit.Test;

public class EditorCloserTest {

  @Test
  public void delegatesCloseCommand() {
    IWorkbenchPage page = EasyMock.createStrictMock(IWorkbenchPage.class);
    IEditorPart part = EasyMock.createNiceMock(IEditorPart.class);
    EasyMock.expect(page.closeEditor(part, false)).andReturn(true);
    EasyMock.replay(page);
    new PageEditorCloser(page).close(part);
    EasyMock.verify(page);
  }
}