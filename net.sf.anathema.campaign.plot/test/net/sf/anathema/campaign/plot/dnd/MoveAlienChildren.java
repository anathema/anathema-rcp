package net.sf.anathema.campaign.plot.dnd;

import static org.junit.Assert.assertFalse;
import net.sf.anathema.campaign.plot.repository.PlotPart;

import org.junit.Before;
import org.junit.Test;

public class MoveAlienChildren {

  private PlotPart sourcePlot;
  private PlotPart targetPlot;
  private PlotPart movedChild;
  private PlotPartMove plotPartMove;

  @Before
  public void createPlotPartMove() {
    targetPlot = new PlotPart("parentPart", DummyPlotUnit.Plot, null); //$NON-NLS-1$
    sourcePlot = new PlotPart("parentPart", DummyPlotUnit.Plot, null); //$NON-NLS-1$
    movedChild = sourcePlot.addChild("child1"); //$NON-NLS-1$
    this.plotPartMove = new PlotPartMove(movedChild, targetPlot);
  }

  @Test
  public void rejectsAlienChild() throws Exception {
    assertFalse(plotPartMove.isValid());
  }
}
