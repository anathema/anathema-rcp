package net.sf.anathema.campaign.plot.dnd;

import static org.junit.Assert.*;
import net.sf.anathema.campaign.plot.repository.IPlotPart;
import net.sf.anathema.campaign.plot.repository.PlotPart;
import net.sf.anathema.campaign.plot.repository.PlotUnit;

import org.junit.Before;
import org.junit.Test;

public class MoveToSelfTest {

  private PlotPartMove plotPartMove;
  private PlotPart parent;
  private PlotPart formerChild;
  private PlotPart laterChild;
  private PlotPart middleChild;

  @Before
  public void createPlotPartMove() {
    parent = new PlotPart("parentPart", PlotUnit.Plot, null); //$NON-NLS-1$
    formerChild = parent.addChild("child1"); //$NON-NLS-1$
    middleChild = parent.addChild("middleChild"); //$NON-NLS-1$
    laterChild = parent.addChild("child2"); //$NON-NLS-1$
    this.plotPartMove = new PlotPartMove(middleChild, middleChild);
  }

  @Test
  public void isValid() throws Exception {
    assertTrue(plotPartMove.isValid());
  }

  @Test
  public void noMoveOnAllLocations() throws Exception {
    for (RelativeLocation location : RelativeLocation.values()) {
      plotPartMove.moveTo(location);
      assertArrayEquals(location.toString(), new IPlotPart[] { formerChild, middleChild, laterChild }, parent.getChildren());
    }
  }
}