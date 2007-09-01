package net.sf.anathema.basics.repository.linkage;

import net.sf.anathema.basics.jface.IFileEditorInput;
import net.sf.anathema.basics.repository.itemtype.internal.ItemType;
import net.sf.anathema.basics.repository.treecontent.DummyResourceViewElement;
import net.sf.anathema.basics.repository.treecontent.itemtype.ItemTypeViewElement;

import org.easymock.EasyMock;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.junit.Before;
import org.junit.Test;

public class EditorViewLinkerTest {

  private EditorViewLinker linker;
  private IWorkbenchWindow window;
  private ISelectionProvider provider;

  @Before
  public void createLinker() {
    window = EasyMock.createMock(IWorkbenchWindow.class);
    provider = EasyMock.createMock(ISelectionProvider.class);
    linker = new EditorViewLinker(window, provider);
    linker.toggleLink();
  }

  @Test
  public void bringsEditorToFront() throws Exception {
    IWorkbenchPage page = EasyMock.createMock(IWorkbenchPage.class);
    EasyMock.expect(window.getActivePage()).andReturn(page);
    IFileEditorInput input = EasyMock.createMock(IFileEditorInput.class);

    DummyResourceViewElement element = new DummyResourceViewElement();
    element.setInput(input);
    EasyMock.expect(provider.getSelection()).andReturn(new StructuredSelection(element));

    IEditorPart editor = EasyMock.createMock(IEditorPart.class);
    EasyMock.expect(page.findEditor(input)).andReturn(editor);
    page.bringToTop(editor);
    EasyMock.replay(window, provider, page);
    linker.update();
    EasyMock.verify(page);
  }

  @Test
  public void doesNotCrashWithoutActivePage() throws Exception {
    EasyMock.expect(window.getActivePage()).andReturn(null);
    EasyMock.replay(window);
    linker.update();
  }

  @Test
  public void doesNotCrashIfSelectionIsEmpty() throws Exception {
    IWorkbenchPage page = EasyMock.createMock(IWorkbenchPage.class);
    EasyMock.expect(window.getActivePage()).andReturn(page);
    EasyMock.expect(provider.getSelection()).andReturn(new StructuredSelection());
    EasyMock.replay(window, provider);
    linker.update();
  }

  @Test
  public void doesNotTryToGetInputFromBadInputProvider() throws Exception {
    IWorkbenchPage page = EasyMock.createMock(IWorkbenchPage.class);
    EasyMock.expect(window.getActivePage()).andReturn(page);
    EasyMock.expect(provider.getSelection()).andReturn(
        new StructuredSelection(new ItemTypeViewElement(new ItemType(null, null))));
    EasyMock.replay(window, provider, page);
    linker.update();
  }

  @Test
  public void doesNothingIfEditorIsClosed() throws Exception {
    IWorkbenchPage page = EasyMock.createMock(IWorkbenchPage.class);
    EasyMock.expect(window.getActivePage()).andReturn(page);
    IFileEditorInput input = EasyMock.createMock(IFileEditorInput.class);

    DummyResourceViewElement element = new DummyResourceViewElement();
    element.setInput(input);
    EasyMock.expect(provider.getSelection()).andReturn(new StructuredSelection(element));

    EasyMock.expect(page.findEditor(input)).andReturn(null);
    page.bringToTop(null);
    EasyMock.replay(window, provider, page);
    linker.update();
    EasyMock.verify(page);
  }

  @Test
  public void canHandleNullEditorInput() throws Exception {
    IWorkbenchPage page = EasyMock.createMock(IWorkbenchPage.class);
    EasyMock.expect(window.getActivePage()).andReturn(page);

    DummyResourceViewElement element = new DummyResourceViewElement();
    element.setInput(null);
    EasyMock.expect(provider.getSelection()).andReturn(new StructuredSelection(element));
    EasyMock.expect(page.findEditor(null)).andReturn(null);
    page.bringToTop(null);
    EasyMock.replay(window, provider, page);
    linker.update();
    EasyMock.verify(page);
  }
}