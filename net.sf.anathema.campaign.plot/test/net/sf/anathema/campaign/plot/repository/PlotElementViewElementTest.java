package net.sf.anathema.campaign.plot.repository;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.ui.IEditorInputProvider;
import net.sf.anathema.basics.repository.treecontent.deletion.IPageDelible;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IFolder;
import org.junit.Test;

public class PlotElementViewElementTest {

  @Test
  public void isPartOfOwnFolder() throws Exception {
    IFolder folder = EasyMock.createMock(IFolder.class);
    PlotElementViewElement element = new PlotElementViewElement(folder, null, null, null);
    assertTrue(element.isPartOf(folder));
  }

  @Test
  public void isNotPartOfDifferentFolder() throws Exception {
    IFolder folder = EasyMock.createMock(IFolder.class);
    PlotElementViewElement element = new PlotElementViewElement(folder, null, null, null);
    assertFalse(element.isPartOf(EasyMock.createMock(IFolder.class)));
  }

  @Test
  public void canBeDeleted() throws Exception {
    assertTrue(new PlotElementViewElement(null, null, null, null).getAdapter(IPageDelible.class) instanceof IPageDelible);
  }

  @Test
  public void providesEditorInputs() throws Exception {
    assertTrue(new PlotElementViewElement(null, null, null, null).getAdapter(IEditorInputProvider.class) instanceof IEditorInputProvider);
  }
}
