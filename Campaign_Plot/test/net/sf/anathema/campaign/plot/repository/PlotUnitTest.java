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

  @Test
  public void haveCorrectPersistenceStrings() throws Exception {
    Assert.assertEquals("Plot", PlotUnit.Plot.getPersistenceString()); //$NON-NLS-1$
    Assert.assertEquals("Story", PlotUnit.Story.getPersistenceString()); //$NON-NLS-1$
    Assert.assertEquals("Episode", PlotUnit.Episode.getPersistenceString()); //$NON-NLS-1$
    Assert.assertEquals("Scene", PlotUnit.Scene.getPersistenceString()); //$NON-NLS-1$
  }
}