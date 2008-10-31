package net.sf.anathema.campaign.plot.repository;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.item.editor.DummyCloser;
import net.sf.anathema.basics.jface.IFileEditorInput;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;
import org.junit.Before;

public class AbstractCloseHandlerTest {

  protected PlotElementCloseHandler handler;
  protected DummyPlotElementViewElement element;
  protected DummyCloser closer;
  protected IFileEditorInput input;
  protected IFile editorFile;
  protected IFolder parentFolder;
  protected IEditorReference reference;
  protected List<Object> additionalMocks = new ArrayList<Object>();

  protected void assertCloserIsClosed(boolean closed) throws PartInitException {
    Object[] allAdditionalMocks = additionalMocks.toArray(new Object[additionalMocks.size()]);
    EasyMock.replay(reference, input, editorFile);
    EasyMock.replay(allAdditionalMocks);
    handler.closeIfRequired(reference);
    assertEquals(closed, closer.isClosed());
    EasyMock.verify(reference, input, editorFile);
    EasyMock.verify(allAdditionalMocks);
  }

  protected IFile createMockFile(IFolder parent) {
    IFile file = EasyMock.createMock(IFile.class);
    EasyMock.expect(file.getParent()).andReturn(parent);
    additionalMocks.add(file);
    return file;
  }

  protected DummyPlotElementViewElement addDummyViewElementTo(DummyPlotElementViewElement parent) {
    DummyPlotElementViewElement child = new DummyPlotElementViewElement();
    child.setParentFolder(parentFolder);
    parent.addChild(child);
    return child;
  }

  @Before
  public void createHandler() throws Exception {
    this.closer = new DummyCloser();
    this.element = new DummyPlotElementViewElement();
    this.handler = new PlotElementCloseHandler(closer, element);
    this.input = EasyMock.createNiceMock(IFileEditorInput.class);
    this.editorFile = EasyMock.createMock(IFile.class);
    this.parentFolder = EasyMock.createMock(IFolder.class);
    EasyMock.expect(editorFile.getParent()).andReturn(parentFolder).anyTimes();
    element.setParentFolder(parentFolder);
    reference = EasyMock.createMock(IEditorReference.class);
    EasyMock.expect(reference.getId()).andReturn("net.sf.anathema.campaign.plot.ploteditor").anyTimes(); //$NON-NLS-1$
    EasyMock.expect(reference.getEditorInput()).andReturn(input).anyTimes();
  }
}