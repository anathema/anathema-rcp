package net.sf.anathema.campaign.plot.repository;

public interface IPlotPart {
  
  public PlotUnit getPlotUnit();

  public IPlotPart[] getChildren();
  
  public String getRepositoryId();

  public IPlotPart getParent();

  public void moveChild(IPlotPart child, int targetIndex);

  public int indexOf(IPlotPart child);

  public IPlotPart getRoot();
}