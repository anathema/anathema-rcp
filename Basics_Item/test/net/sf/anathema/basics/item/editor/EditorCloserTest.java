package net.sf.anathema.basics.item.editor;

import net.sf.anathema.basics.item.editor.EditorCloser;

import org.easymock.EasyMock;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartSite;
import org.junit.Test;

public class EditorCloserTest {

  @Test
  public void delegatesCloseCommand() {
    IEditorPart part = EasyMock.createNiceMock(IEditorPart.class);
    IWorkbenchPartSite site = EasyMock.createMock(IWorkbenchPartSite.class);
    IWorkbenchPage page = EasyMock.createStrictMock(IWorkbenchPage.class);
    EasyMock.expect(part.getSite()).andReturn(site);
    EasyMock.expect(site.getPage()).andReturn(page);
    EasyMock.expect(page.closeEditor(part, false)).andReturn(true);
    EasyMock.replay(part, site, page);
    new EditorCloser().close(part);
    EasyMock.verify(part,site,page);
  }
}