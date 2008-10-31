package net.sf.anathema.campaign.plot.repository;

import java.net.URL;

public interface IPlotUnit {

  public IPlotUnit getSuccessor();

  public String getName();

  public URL getImage();
}