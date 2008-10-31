package net.sf.anathema.campaign.plot.dnd;

import static org.junit.Assert.*;
import net.sf.anathema.campaign.plot.repository.PlotPart;

import org.junit.Before;
import org.junit.Test;

public class MoveFormerSiblingToLaterSibling {

  private PlotPartMove plotPartMove;
  private PlotPart parent;
  private PlotPart formerChild;
  private PlotPart laterChild;
  private PlotPart middleChild;

  @Before
  public void createPlotPartMove() {
    parent = new PlotPart("parentPart", DummyPlotUnit.Plot, null); //$NON-NLS-1$
    formerChild = parent.addChild("child1"); //$NON-NLS-1$
    middleChild = parent.addChild("middleChild"); //$NON-NLS-1$
    laterChild = parent.addChild("child2"); //$NON-NLS-1$
    this.plotPartMove = new PlotPartMove(formerChild, laterChild);
  }

  @Test
  public void isValid() throws Exception {
    assertTrue(plotPartMove.isValid());
  }

  @Test
  public void isMovedAfterIfLocationIsOn() throws Exception {
    plotPartMove.moveTo(RelativeLocation.On);
    assertArrayEquals(new PlotPart[] { middleChild, laterChild, formerChild }, parent.getChildren());
  }

  @Test
  public void isMovedBeforeIfLocationIsBefore() throws Exception {
    plotPartMove.moveTo(RelativeLocation.Before);
    assertArrayEquals(new PlotPart[] { middleChild, formerChild, laterChild }, parent.getChildren());
  }
}