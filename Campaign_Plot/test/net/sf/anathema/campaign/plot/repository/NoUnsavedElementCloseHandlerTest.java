package net.sf.anathema.campaign.plot.repository;

import static org.junit.Assert.*;
import net.sf.anathema.basics.jface.IFileEditorInput;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.ui.IEditorReference;
import org.junit.Before;
import org.junit.Test;

public class NoUnsavedElementCloseHandlerTest {
  private PlotElementCloseHandler handler;
  private DummyPlotElementViewElement element;
  private DummyCloser closer;
  private IFileEditorInput input;

  @Before
  public void createHandler() {
    this.closer = new DummyCloser();
    this.element = new DummyPlotElementViewElement();
    this.handler = new PlotElementCloseHandler(closer, element);
    this.input = EasyMock.createNiceMock(IFileEditorInput.class);
  }

  @Test
  public void closesEditorHandlingElement() throws Exception {
    IEditorReference reference = EasyMock.createMock(IEditorReference.class);
    EasyMock.expect(reference.getEditorInput()).andReturn(input);
    EasyMock.expect(input.getAdapter(IFileEditorInput.class)).andReturn(input).anyTimes();
    IFile file = EasyMock.createMock(IFile.class);
    EasyMock.expect(input.getFile()).andReturn(file);

    IFolder parent = EasyMock.createMock(IFolder.class);
    EasyMock.expect(file.getParent()).andReturn(parent);

    element.setParentFolder(parent);
    EasyMock.expect(reference.getEditor(false)).andReturn(null);
    EasyMock.replay(reference, input, file);
    handler.closeIfRequired(reference);
    assertTrue(closer.isClosed());
    EasyMock.verify(reference, input, file);
  }

  // Going with the earlier names, I would have loved to call this "unborn children".
  @Test
  public void closesEditorHandlingUnsavedChild() throws Exception {
    IEditorReference reference = EasyMock.createNiceMock(IEditorReference.class);
    EasyMock.expect(reference.getEditorInput()).andReturn(input).anyTimes();
    EasyMock.expect(input.getAdapter(IFileEditorInput.class)).andReturn(null);
    EasyMock.expect(input.getAdapter(IPlotChild.class)).andReturn(new PlotPartPlotChild(element.getPlotElement()));
    EasyMock.expect(reference.getEditor(false)).andReturn(null);

    EasyMock.expect(reference.getName()).andReturn("Unborn").anyTimes(); //$NON-NLS-1$
    EasyMock.replay(reference, input);
    handler.closeIfRequired(reference);
    assertTrue(closer.isClosed());
    EasyMock.verify(reference, input);
  }

  @Test
  public void doesNotCloseEditorsByNameAlone() throws Exception {
    IEditorReference reference = EasyMock.createMock(IEditorReference.class);
    EasyMock.expect(reference.getEditorInput()).andReturn(input).anyTimes();
    EasyMock.expect(input.getAdapter(IFileEditorInput.class)).andReturn(input).anyTimes();
    IFile file = EasyMock.createMock(IFile.class);
    EasyMock.expect(input.getFile()).andReturn(file);

    EasyMock.expect(file.getParent()).andReturn(null);
    element.setName("DisplayName"); //$NON-NLS-1$
    EasyMock.expect(reference.getName()).andReturn("DisplayName").anyTimes(); //$NON-NLS-1$
    EasyMock.replay(reference, input, file);
    handler.closeIfRequired(reference);
    assertFalse(closer.isClosed());
    EasyMock.verify(reference, input, file);
  }

  @Test
  public void closesEditorHandlingChild() throws Exception {
    IEditorReference reference = EasyMock.createNiceMock(IEditorReference.class);
    EasyMock.expect(reference.getEditorInput()).andReturn(input).anyTimes();

    EasyMock.expect(input.getAdapter(IFileEditorInput.class)).andReturn(input).anyTimes();
    IFile file = EasyMock.createMock(IFile.class);
    EasyMock.expect(input.getFile()).andReturn(file).anyTimes();

    IFolder parent = EasyMock.createMock(IFolder.class);
    EasyMock.expect(file.getParent()).andReturn(parent).anyTimes();

    EasyMock.expect(reference.getEditor(false)).andReturn(null);

    DummyPlotElementViewElement child = new DummyPlotElementViewElement();
    child.setParentFolder(parent);
    element.addChild(child);
    EasyMock.replay(reference, input, file);
    handler.closeIfRequired(reference);
    assertTrue(closer.isClosed());
    EasyMock.verify(reference, input, file);
  }

  @Test
  public void closesEditorHandlingYoungerChild() throws Exception {
    IEditorReference reference = EasyMock.createNiceMock(IEditorReference.class);
    EasyMock.expect(reference.getEditorInput()).andReturn(input).anyTimes();

    EasyMock.expect(input.getAdapter(IFileEditorInput.class)).andReturn(input).anyTimes();
    IFile file = EasyMock.createMock(IFile.class);
    EasyMock.expect(input.getFile()).andReturn(file).anyTimes();

    IFolder parent = EasyMock.createMock(IFolder.class);
    EasyMock.expect(file.getParent()).andReturn(parent).anyTimes();

    EasyMock.expect(reference.getEditor(false)).andReturn(null);

    element.addChild(new DummyPlotElementViewElement());
    DummyPlotElementViewElement secondChild = new DummyPlotElementViewElement();
    secondChild.setParentFolder(parent);
    element.addChild(secondChild);
    EasyMock.replay(reference, input, file);
    handler.closeIfRequired(reference);
    assertTrue(closer.isClosed());
    EasyMock.verify(reference, input, file);
  }

  @Test
  public void closesEditorHandlingGrandchild() throws Exception {
    IEditorReference reference = EasyMock.createNiceMock(IEditorReference.class);
    EasyMock.expect(reference.getEditorInput()).andReturn(input).anyTimes();

    EasyMock.expect(input.getAdapter(IFileEditorInput.class)).andReturn(input).anyTimes();
    IFile file = EasyMock.createMock(IFile.class);
    EasyMock.expect(input.getFile()).andReturn(file).anyTimes();

    IFolder parent = EasyMock.createMock(IFolder.class);
    EasyMock.expect(file.getParent()).andReturn(parent).anyTimes();

    EasyMock.expect(reference.getEditor(false)).andReturn(null);

    EasyMock.expect(reference.getName()).andReturn("GrandChild").anyTimes(); //$NON-NLS-1$
    DummyPlotElementViewElement grandchild = new DummyPlotElementViewElement();
    grandchild.setParentFolder(parent);
    DummyPlotElementViewElement child = new DummyPlotElementViewElement();
    child.setParentFolder(parent);
    element.addChild(child);
    child.addChild(grandchild);
    EasyMock.replay(reference, input, file);
    handler.closeIfRequired(reference);
    assertTrue(closer.isClosed());
    EasyMock.verify(reference, input, file);
  }
}
