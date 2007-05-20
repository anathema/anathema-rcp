package net.sf.anathema.campaign.plot.repository;

public interface IPlotPart {

  public IPlotPart[] getChildren();
  
  public String getRepositoryId();
}