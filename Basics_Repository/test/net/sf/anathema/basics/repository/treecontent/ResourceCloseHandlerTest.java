package net.sf.anathema.basics.repository.treecontent;

import static org.junit.Assert.*;
import net.sf.anathema.basics.item.editor.DummyCloser;
import net.sf.anathema.basics.jface.IFileEditorInput;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;
import org.junit.Before;
import org.junit.Test;

public class ResourceCloseHandlerTest {

  private ResourceCloseHandler resourceCloseHandler;
  private DummyCloser closer;
  private IFileEditorInput input;
  private IFile editorFile;
  private IEditorReference reference;

  @Before
  public void createHandler() throws PartInitException {
    this.closer = new DummyCloser();
    DummyResourceViewElement element = new DummyResourceViewElement();
    this.resourceCloseHandler = new ResourceCloseHandler(closer, element);
    this.input = EasyMock.createMock(IFileEditorInput.class);
    this.editorFile = EasyMock.createMock(IFile.class);
    this.reference = EasyMock.createNiceMock(IEditorReference.class);
    element.setFile(editorFile);
    EasyMock.expect(reference.getEditorInput()).andReturn(input).anyTimes();
    EasyMock.expect(input.getAdapter(IFileEditorInput.class)).andReturn(input).anyTimes();
    EasyMock.replay(reference);
  }

  @Test
  public void closesEditorForElement() throws Exception {
    EasyMock.expect(input.getFile()).andReturn(editorFile);
    EasyMock.replay(input);
    resourceCloseHandler.closeIfRequired(reference);
    assertTrue(closer.isClosed());
  }

  @Test
  public void doesNotCloseEditorsForDifferentElements() throws Exception {
    EasyMock.expect(input.getFile()).andReturn(null);
    EasyMock.replay(input);
    resourceCloseHandler.closeIfRequired(reference);
    assertFalse(closer.isClosed());
  }
}
