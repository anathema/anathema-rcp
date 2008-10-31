package net.sf.anathema.campaign.plot.repository;

import net.sf.anathema.basics.jface.IFileEditorInput;

import org.easymock.EasyMock;
import org.junit.Test;

public class PlotElementCloseHandlerTest extends AbstractCloseHandlerTest {

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
}