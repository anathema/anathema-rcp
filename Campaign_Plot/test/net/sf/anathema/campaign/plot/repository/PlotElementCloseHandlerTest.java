package net.sf.anathema.campaign.plot.repository;

import static org.junit.Assert.*;
import net.sf.anathema.basics.jface.IFileEditorInput;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;
import org.junit.Before;
import org.junit.Test;

public class PlotElementCloseHandlerTest {
  private PlotElementCloseHandler handler;
  private DummyPlotElementViewElement element;
  private DummyCloser closer;
  private IFileEditorInput input;
  private IFile editorFile;
  private IFolder parentFolder;
  private IEditorReference reference;

  private void assertCloserIsClosed(boolean closed, Object... additionalMocks) throws PartInitException {
    EasyMock.replay(reference, input, editorFile);
    EasyMock.replay(additionalMocks);
    handler.closeIfRequired(reference);
    assertEquals(closed, closer.isClosed());
    EasyMock.verify(reference, input, editorFile);
    EasyMock.verify(additionalMocks);
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
    EasyMock.expect(reference.getEditorInput()).andReturn(input).anyTimes();
  }

  @Test
  public void closesEditorHandlingElement() throws Exception {
    EasyMock.expect(input.getAdapter(IFileEditorInput.class)).andReturn(input).anyTimes();
    IFile file = EasyMock.createMock(IFile.class);
    EasyMock.expect(input.getFile()).andReturn(file);

    IFolder parent = EasyMock.createMock(IFolder.class);
    EasyMock.expect(file.getParent()).andReturn(parent);

    element.setParentFolder(parent);
    EasyMock.expect(reference.getEditor(false)).andReturn(null);
    assertCloserIsClosed(true, file);
  }

  // Going with the earlier names, I would have loved to call this "unborn children".
  @Test
  public void closesEditorHandlingUnsavedChild() throws Exception {
    EasyMock.expect(input.getAdapter(IFileEditorInput.class)).andReturn(null);
    EasyMock.expect(input.getAdapter(IPlotChild.class)).andReturn(new PlotPartPlotChild(element.getPlotElement()));
    EasyMock.expect(reference.getEditor(false)).andReturn(null);
    assertCloserIsClosed(true);
  }

  @Test
  public void closesEditorHandlingUnsavedGrandchild() throws Exception {
    EasyMock.expect(input.getAdapter(IFileEditorInput.class)).andReturn(null);
    DummyPlotElementViewElement child = new DummyPlotElementViewElement(element.getPlotElement());
    element.getPlotElement().addChild(child.getPlotElement(), 0);
    element.addChild(child);
    EasyMock.expect(input.getAdapter(IPlotChild.class))
        .andReturn(new PlotPartPlotChild(child.getPlotElement()))
        .anyTimes();
    EasyMock.expect(reference.getEditor(false)).andReturn(null);
    assertCloserIsClosed(true);
  }

  @Test
  public void doesNotCloseEditorsByNameAlone() throws Exception {
    EasyMock.expect(input.getAdapter(IFileEditorInput.class)).andReturn(input).anyTimes();
    IFile file = EasyMock.createMock(IFile.class);
    EasyMock.expect(input.getFile()).andReturn(file);

    EasyMock.expect(file.getParent()).andReturn(null);
    element.setName("DisplayName"); //$NON-NLS-1$
    EasyMock.expect(reference.getName()).andReturn("DisplayName").anyTimes(); //$NON-NLS-1$
    assertCloserIsClosed(false, file);
  }

  @Test
  public void closesEditorHandlingChild() throws Exception {
    EasyMock.expect(input.getAdapter(IFileEditorInput.class)).andReturn(input).anyTimes();
    EasyMock.expect(input.getFile()).andReturn(editorFile).anyTimes();

    EasyMock.expect(reference.getEditor(false)).andReturn(null);

    DummyPlotElementViewElement child = new DummyPlotElementViewElement();
    child.setParentFolder(parentFolder);
    element.addChild(child);
    assertCloserIsClosed(true);
  }

  @Test
  public void closesEditorHandlingYoungerChild() throws Exception {
    EasyMock.expect(input.getAdapter(IFileEditorInput.class)).andReturn(input).anyTimes();
    EasyMock.expect(input.getFile()).andReturn(editorFile).anyTimes();

    EasyMock.expect(reference.getEditor(false)).andReturn(null);

    element.addChild(new DummyPlotElementViewElement());
    DummyPlotElementViewElement secondChild = new DummyPlotElementViewElement();
    secondChild.setParentFolder(parentFolder);
    element.addChild(secondChild);
    assertCloserIsClosed(true);
  }

  @Test
  public void closesEditorHandlingGrandchild() throws Exception {
    EasyMock.expect(input.getAdapter(IFileEditorInput.class)).andReturn(input).anyTimes();
    EasyMock.expect(input.getFile()).andReturn(editorFile).anyTimes();

    EasyMock.expect(reference.getEditor(false)).andReturn(null);

    EasyMock.expect(reference.getName()).andReturn("GrandChild").anyTimes(); //$NON-NLS-1$
    DummyPlotElementViewElement grandchild = new DummyPlotElementViewElement();
    grandchild.setParentFolder(parentFolder);
    DummyPlotElementViewElement child = new DummyPlotElementViewElement();
    child.setParentFolder(parentFolder);
    element.addChild(child);
    child.addChild(grandchild);
    assertCloserIsClosed(true);
  }
}