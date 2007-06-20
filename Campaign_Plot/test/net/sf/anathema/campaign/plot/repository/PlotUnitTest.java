package net.sf.anathema.campaign.plot.repository;

import org.junit.Assert;
import org.junit.Test;

public class PlotUnitTest {

  @Test
  public void haveCorrectSuccessors() throws Exception {
    Assert.assertEquals(PlotUnit.Story, PlotUnit.Plot.getSuccessor());
    Assert.assertEquals(PlotUnit.Episode, PlotUnit.Story.getSuccessor());
    Assert.assertEquals(PlotUnit.Scene, PlotUnit.Episode.getSuccessor());
    Assert.assertEquals(PlotUnit.Scene, PlotUnit.Scene.getSuccessor());
  }
}