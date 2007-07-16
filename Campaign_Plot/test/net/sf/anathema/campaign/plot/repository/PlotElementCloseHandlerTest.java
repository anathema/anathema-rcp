package net.sf.anathema.campaign.plot.repository;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
  private List<Object> additionalMocks = new ArrayList<Object>();

  private void assertCloserIsClosed(boolean closed) throws PartInitException {
    Object[] allAdditionalMocks = additionalMocks.toArray(new Object[additionalMocks.size()]);
    EasyMock.replay(reference, input, editorFile);
    EasyMock.replay(allAdditionalMocks);
    handler.closeIfRequired(reference);
    assertEquals(closed, closer.isClosed());
    EasyMock.verify(reference, input, editorFile);
    EasyMock.verify(allAdditionalMocks);
  }

  private IFile createMockFile(IFolder parent) {
    IFile file = EasyMock.createMock(IFile.class);
    EasyMock.expect(file.getParent()).andReturn(parent);
    additionalMocks.add(file);
    return file;
  }

  private DummyPlotElementViewElement addDummyViewElementTo(DummyPlotElementViewElement parent) {
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
    EasyMock.expect(reference.getEditorInput()).andReturn(input).anyTimes();
  }

  @Test
  public void closesEditorHandlingElement() throws Exception {
    IFolder parent = EasyMock.createMock(IFolder.class);
    EasyMock.expect(input.getAdapter(IFileEditorInput.class)).andReturn(input).anyTimes();
    EasyMock.expect(input.getFile()).andReturn(createMockFile(parent));
    EasyMock.expect(reference.getEditor(false)).andReturn(null);
    element.setParentFolder(parent);
    assertCloserIsClosed(true);
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
    EasyMock.expect(reference.getEditor(false)).andReturn(null);
    DummyPlotElementViewElement child = new DummyPlotElementViewElement(element.getPlotElement());
    element.getPlotElement().addChild(child.getPlotElement(), 0);
    element.addChild(child);
    EasyMock.expect(input.getAdapter(IPlotChild.class))
        .andReturn(new PlotPartPlotChild(child.getPlotElement()))
        .anyTimes();
    assertCloserIsClosed(true);
  }

  @Test
  public void doesNotCloseEditorsByNameAlone() throws Exception {
    EasyMock.expect(input.getAdapter(IFileEditorInput.class)).andReturn(input).anyTimes();
    EasyMock.expect(input.getFile()).andReturn(createMockFile(null));
    EasyMock.expect(reference.getName()).andReturn("DisplayName").anyTimes(); //$NON-NLS-1$
    element.setName("DisplayName"); //$NON-NLS-1$
    assertCloserIsClosed(false);
  }

  @Test
  public void closesEditorHandlingChild() throws Exception {
    EasyMock.expect(input.getAdapter(IFileEditorInput.class)).andReturn(input).anyTimes();
    EasyMock.expect(input.getFile()).andReturn(editorFile).anyTimes();
    EasyMock.expect(reference.getEditor(false)).andReturn(null);
    
    addDummyViewElementTo(element);
    assertCloserIsClosed(true);
  }

  @Test
  public void closesEditorHandlingYoungerChild() throws Exception {
    EasyMock.expect(input.getAdapter(IFileEditorInput.class)).andReturn(input).anyTimes();
    EasyMock.expect(input.getFile()).andReturn(editorFile).anyTimes();
    EasyMock.expect(reference.getEditor(false)).andReturn(null);
    
    element.addChild(new DummyPlotElementViewElement());
    addDummyViewElementTo(element);
    assertCloserIsClosed(true);
  }
  
  @Test
  public void closesEditorHandlingGrandchild() throws Exception {
    EasyMock.expect(input.getAdapter(IFileEditorInput.class)).andReturn(input).anyTimes();
    EasyMock.expect(input.getFile()).andReturn(editorFile).anyTimes();
    EasyMock.expect(reference.getEditor(false)).andReturn(null);
    EasyMock.expect(reference.getName()).andReturn("GrandChild").anyTimes(); //$NON-NLS-1$

    DummyPlotElementViewElement child = addDummyViewElementTo(element);
    addDummyViewElementTo(child);
    assertCloserIsClosed(true);
  }
}