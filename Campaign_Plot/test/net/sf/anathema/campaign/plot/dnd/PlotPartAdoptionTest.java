package net.sf.anathema.campaign.plot.dnd;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.campaign.plot.repository.PlotPart;

import org.junit.Before;
import org.junit.Test;

public class PlotPartAdoptionTest {

  private PlotPart targetParent;
  private PlotPart sourceElement;
  private PlotPart sourceParent;

  @Before
  public void performAdoption() {
    PlotPart plot = new PlotPart("parentPart", DummyPlotUnit.Plot, null); //$NON-NLS-1$
    targetParent = plot.addChild("targetParent"); //$NON-NLS-1$
    sourceParent = plot.addChild("sourceParent"); //$NON-NLS-1$
    sourceElement = sourceParent.addChild("sourceElement"); //$NON-NLS-1$    
    sourceElement.moveTo(targetParent, 0);
  }

  @Test
  public void childMigratesToNewParent() throws Exception {
    assertEquals(targetParent, sourceElement.getParent());
  }

  @Test
  public void oldParentNoLongerHasChild() throws Exception {
    assertEquals(0, sourceParent.getChildren().length);
  }
}