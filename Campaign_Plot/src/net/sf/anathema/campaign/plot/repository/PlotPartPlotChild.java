package net.sf.anathema.campaign.plot.repository;

public class PlotPartPlotChild implements IPlotChild {

  private final IPlotPart parent;

  public PlotPartPlotChild(IPlotPart parent) {
    this.parent = parent;
  }

  @Override
  public IPlotPart getParent() {
    return parent;
  }
}