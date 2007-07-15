package net.sf.anathema.campaign.plot.repository;

import static org.junit.Assert.*;

import org.easymock.EasyMock;

public class PlotPartPlotChildTest {

  @org.junit.Test
  public void returnsParent() throws Exception {
    IPlotPart part = EasyMock.createMock(IPlotPart.class);
    assertEquals(part, new PlotPartPlotChild(part).getParent());
  }
}