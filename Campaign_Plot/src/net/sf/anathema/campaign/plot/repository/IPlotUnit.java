package net.sf.anathema.campaign.plot.repository;

import java.net.URL;

import net.sf.anathema.lib.util.IIdentificate;

public interface IPlotUnit extends IIdentificate {

  public IPlotUnit getSuccessor();

  public String getName();

  public URL getImage();
}