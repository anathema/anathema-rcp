package net.sf.anathema.basics.eclipse.ui;

import static org.easymock.EasyMock.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.junit.Test;

public class PartContainer_EditorInputTest {

  @Test
  public void returnsNullEditorInputIfThereIsNoPage() throws Exception {
    assertContainerReturnsNullObject(null);
  }

  @Test
  public void returnsNullEditorInputIfNoEditorIsActive() throws Exception {
    IWorkbenchPage page = createWorkbenchPage(null);
    assertContainerReturnsNullObject(page);
  }

  private void assertContainerReturnsNullObject(IWorkbenchPage page) {
    IWorkbenchWindow window = createWorkbenchWindow(page);
    IEditorInput input = new PartContainer(window).getEditorInput();
    assertThat(input, is(instanceOf(NullEditorInput.class)));
  }

  @Test
  public void returnsEditorInputFromActiveEditor() throws Exception {
    IEditorInput input = EasyMock.createNiceMock(IEditorInput.class);
    IEditorPart part = createEditorPart(input);
    IWorkbenchPage page = createWorkbenchPage(part);
    IWorkbenchWindow window = createWorkbenchWindow(page);
    assertThat(new PartContainer(window).getEditorInput(), is(input));
  }

  private IEditorPart createEditorPart(IEditorInput input) {
    IEditorPart part = createNiceMock(IEditorPart.class);
    expect(part.getEditorInput()).andReturn(input);
    replay(part);
    return part;
  }

  private IWorkbenchPage createWorkbenchPage(IEditorPart part) {
    IWorkbenchPage page = createNiceMock(IWorkbenchPage.class);
    expect(page.getActiveEditor()).andReturn(part).anyTimes();
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