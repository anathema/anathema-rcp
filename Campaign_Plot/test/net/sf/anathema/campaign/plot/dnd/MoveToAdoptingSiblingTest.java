package net.sf.anathema.campaign.plot.dnd;

import static org.junit.Assert.*;
import net.sf.anathema.campaign.plot.repository.IPlotPart;
import net.sf.anathema.campaign.plot.repository.PlotPart;

import org.junit.Before;
import org.junit.Test;

public class MoveToAdoptingSiblingTest {

  private PlotPart targetParent;
  private PlotPartMove plotPartMove;
  private PlotPart sourceParent;
  private PlotPart targetElement;
  private PlotPart sourceElement;

  @Before
  public void createPlotPartMove() {
    PlotPart plot = new PlotPart("parentPart", DummyPlotUnit.Plot, null); //$NON-NLS-1$
    targetParent = plot.addChild("targetParent"); //$NON-NLS-1$
    sourceParent = plot.addChild("sourceParent"); //$NON-NLS-1$
    targetElement = targetParent.addChild("targetElement"); //$NON-NLS-1$
    sourceElement = sourceParent.addChild("sourceElement"); //$NON-NLS-1$
    this.plotPartMove = new PlotPartMove(sourceElement, targetElement);
  }

  @Test
  public void isValidForAllLocations() throws Exception {
    assertTrue(plotPartMove.isValid());
  }

  @Test
  public void moveBeforeAdoptingSibling() throws Exception {
    plotPartMove.moveTo(RelativeLocation.Before);
    assertArrayEquals(new IPlotPart[0], sourceElement.getChildren());
    assertArrayEquals(new IPlotPart[] { sourceElement, targetElement }, targetParent.getChildren());
  }

  @Test
  public void moveAfterAdoptingSibling() throws Exception {
    plotPartMove.moveTo(RelativeLocation.After);
    assertArrayEquals(new IPlotPart[0], sourceElement.getChildren());
    assertArrayEquals(new IPlotPart[] { targetElement, sourceElement }, targetParent.getChildren());
  }

  @Test
  public void moveOnAdoptingSibling() throws Exception {
    plotPartMove.moveTo(RelativeLocation.After);
    assertArrayEquals(new IPlotPart[0], sourceElement.getChildren());
    assertArrayEquals(new IPlotPart[] { targetElement, sourceElement }, targetParent.getChildren());
  }
}