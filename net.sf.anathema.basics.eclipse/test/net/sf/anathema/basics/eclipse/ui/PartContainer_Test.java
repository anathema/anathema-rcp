package net.sf.anathema.basics.eclipse.ui;

import static org.easymock.EasyMock.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.junit.Test;

public class PartContainer_Test {

  @Test
  public void returnsNullEditorInputIfThereIsNoPage() throws Exception {
    assertContainerReturnsNullObject(null);
  }

  @Test
  public void returnsNullEditorInputIfNoEditorIsActive() throws Exception {
    IWorkbenchPage page = createWorkbenchPage();
    assertContainerReturnsNullObject(page);
  }

  private void assertContainerReturnsNullObject(IWorkbenchPage page) {
    IWorkbenchWindow window = createWorkbenchWindow(page);
    IEditorInput input = new PartContainer(window).getEditorInput();
    assertThat(input, is(instanceOf(NullEditorInput.class)));
  }

  private IWorkbenchPage createWorkbenchPage() {
    IWorkbenchPage page = createNiceMock(IWorkbenchPage.class);
    expect(page.getActiveEditor()).andReturn(null);
    replay(page);
    return page;
  }

  private IWorkbenchWindow createWorkbenchWindow(IWorkbenchPage page) {
    IWorkbenchWindow window = createNiceMock(IWorkbenchWindow.class);
    expect(window.getActivePage()).andReturn(page);
    replay(window);
    return window;
  }
}