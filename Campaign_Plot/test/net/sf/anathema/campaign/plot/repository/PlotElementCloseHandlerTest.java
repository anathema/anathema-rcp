package net.sf.anathema.campaign.plot.repository;

import static org.junit.Assert.*;
import net.sf.anathema.basics.jface.IFileEditorInput;

import org.easymock.EasyMock;
import org.eclipse.ui.IEditorReference;
import org.junit.Before;
import org.junit.Test;

public class PlotElementCloseHandlerTest {

  private PlotElementCloseHandler handler;
  private DummyPlotElementViewElement element;
  private DummyCloser closer;
  private IFileEditorInput input;

  @Before
  public void createHandler() {
    this.closer = new DummyCloser();
    this.element = new DummyPlotElementViewElement("DisplayName"); //$NON-NLS-1$
    this.handler = new PlotElementCloseHandler(closer, element);
    this.input = EasyMock.createMock(IFileEditorInput.class);
    EasyMock.expect(input.getAdapter(IPlotChild.class)).andReturn(new PlotPartPlotChild(element.getPlotElement()));
  }

  @Test
  public void closesEditorHandlingElement() throws Exception {
    IEditorReference reference = EasyMock.createMock(IEditorReference.class);
    EasyMock.expect(reference.getEditor(false)).andReturn(null);

    EasyMock.expect(reference.getName()).andReturn("DisplayName").anyTimes(); //$NON-NLS-1$
    EasyMock.replay(reference);
    handler.closeIfRequired(reference);
    assertTrue(closer.isClosed());
    EasyMock.verify(reference);
  }

  @Test
  public void doesNotCloseUnrelatedEditor() throws Exception {
    IEditorReference reference = EasyMock.createNiceMock(IEditorReference.class);
    EasyMock.expect(reference.getEditorInput()).andReturn(input);

    EasyMock.expect(reference.getName()).andReturn("DifferentName").anyTimes(); //$NON-NLS-1$
    EasyMock.replay(reference);
    handler.closeIfRequired(reference);
    assertFalse(closer.isClosed());
    EasyMock.verify(reference);
  }

  @Test
  public void closesEditorHandlingChild() throws Exception {
    IEditorReference reference = EasyMock.createNiceMock(IEditorReference.class);
    EasyMock.expect(reference.getEditorInput()).andReturn(input);
    EasyMock.expect(reference.getEditor(false)).andReturn(null);

    EasyMock.expect(reference.getName()).andReturn("Child").anyTimes(); //$NON-NLS-1$
    element.addChild(new DummyPlotElementViewElement("Child")); //$NON-NLS-1$
    EasyMock.replay(reference);
    handler.closeIfRequired(reference);
    assertTrue(closer.isClosed());
    EasyMock.verify(reference);
  }

  @Test
  public void closesEditorHandlingYoungerChild() throws Exception {
    IEditorReference reference = EasyMock.createNiceMock(IEditorReference.class);
    EasyMock.expect(reference.getEditorInput()).andReturn(input);
    EasyMock.expect(reference.getEditor(false)).andReturn(null);

    EasyMock.expect(reference.getName()).andReturn("Youngster").anyTimes(); //$NON-NLS-1$
    element.addChild(new DummyPlotElementViewElement("Child")); //$NON-NLS-1$
    element.addChild(new DummyPlotElementViewElement("Youngster")); //$NON-NLS-1$
    EasyMock.replay(reference, input);
    handler.closeIfRequired(reference);
    assertTrue(closer.isClosed());
    EasyMock.verify(reference, input);
  }

  @Test
  public void closesEditorHandlingGrandchild() throws Exception {
    IEditorReference reference = EasyMock.createNiceMock(IEditorReference.class);
    EasyMock.expect(reference.getEditorInput()).andReturn(input);
    EasyMock.expect(reference.getEditor(false)).andReturn(null);

    EasyMock.expect(reference.getName()).andReturn("GrandChild").anyTimes(); //$NON-NLS-1$
    DummyPlotElementViewElement child = new DummyPlotElementViewElement("Child"); //$NON-NLS-1$
    element.addChild(child);
    child.addChild(new DummyPlotElementViewElement("GrandChild")); //$NON-NLS-1$
    EasyMock.replay(reference, input);
    handler.closeIfRequired(reference);
    assertTrue(closer.isClosed());
    EasyMock.verify(reference, input);
  }

  // Going with the earlier names, I would have loved to call this "unborn children".
  @Test
  public void closesEditorHandlingUnsavedChild() throws Exception {
    IEditorReference reference = EasyMock.createNiceMock(IEditorReference.class);
    EasyMock.expect(reference.getEditorInput()).andReturn(input);
    EasyMock.expect(reference.getEditor(false)).andReturn(null);

    EasyMock.expect(reference.getName()).andReturn("Unborn").anyTimes(); //$NON-NLS-1$
    EasyMock.replay(reference, input);
    handler.closeIfRequired(reference);
    assertTrue(closer.isClosed());
    EasyMock.verify(reference, input);
  }
}