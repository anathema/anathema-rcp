package net.sf.anathema.campaign.plot.creation;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.campaign.plot.dnd.DummyPlotUnit;
import net.sf.anathema.campaign.plot.repository.IPlotChild;
import net.sf.anathema.campaign.plot.repository.PlotPart;

import org.junit.Test;

public class NewPlotElementEditorInputTest {

  @Test
  public void returnsParentPartInPlotChild() throws Exception {
    PlotPart expectedPart = new PlotPart("expectedId", DummyPlotUnit.Episode, null); //$NON-NLS-1$
    NewPlotElementEditorInput input = new NewPlotElementEditorInput(null, null, null, expectedPart, null);
    assertEquals(expectedPart, ((IPlotChild) input.getAdapter(IPlotChild.class)).getParent());
  }
}