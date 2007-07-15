package net.sf.anathema.campaign.plot.repository;

import static org.junit.Assert.assertTrue;

import org.easymock.EasyMock;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.junit.Test;

public class PlotElementDeleterTest {

  @Test
  public void deletesElement() throws Exception {
    DummyPlotElementViewElement element = new DummyPlotElementViewElement();
    IWorkbenchPage page = EasyMock.createNiceMock(IWorkbenchPage.class);
    EasyMock.expect(page.getEditorReferences()).andReturn(new IEditorReference[0]);
    EasyMock.replay(page);
    PlotElementDeleter deleter = new PlotElementDeleter(page, element);
    deleter.delete();
    assertTrue(element.isDeleted());
  }
}