package net.sf.anathema.campaign.plot.repository;

public interface IPlotPart {

  public void addChild(IPlotPart sourcePart, int child);

  public IPlotPart[] getChildren();

  public IPlotPart getParent();

  public PlotUnit getPlotUnit();

  public String getRepositoryId();

  public IPlotPart getRoot();

  public int indexOf(IPlotPart child);

  public void moveChild(IPlotPart child, int targetIndex);

  public void removeChild(IPlotPart child);

  public void moveTo(IPlotPart targetParent, int targetIndex);
}