package net.sf.anathema.campaign.plot.repository;

import net.sf.anathema.basics.jface.IFileEditorInput;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class CloseSavedFileEditorInputTest extends AbstractCloseHandlerTest {

  @Before
  public void setFileEditorInputWithSameParent() {
    EasyMock.expect(input.getAdapter(IFileEditorInput.class)).andReturn(input).anyTimes();
    EasyMock.expect(input.getFile()).andReturn(editorFile).anyTimes();
    EasyMock.expect(reference.getEditor(false)).andReturn(null);
  }
  
  @Test
  public void closesEditorHandlingElement() throws Exception {
    assertCloserIsClosed(true);
  }

  @Test
  public void closesEditorHandlingChild() throws Exception {
    addDummyViewElementTo(element);
    assertCloserIsClosed(true);
  }

  @Test
  public void closesEditorHandlingYoungerChild() throws Exception {
    element.addChild(new DummyPlotElementViewElement());
    addDummyViewElementTo(element);
    assertCloserIsClosed(true);
  }

  @Test
  public void closesEditorHandlingGrandchild() throws Exception {
    EasyMock.expect(reference.getName()).andReturn("GrandChild").anyTimes(); //$NON-NLS-1$
    DummyPlotElementViewElement child = addDummyViewElementTo(element);
    addDummyViewElementTo(child);
    assertCloserIsClosed(true);
  }
}